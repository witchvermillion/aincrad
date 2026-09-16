package dev.witchvermillion.cardinal.profile.internal;

import static com.mongodb.client.model.Filters.eq;
import static java.util.Objects.requireNonNull;
import static reactor.core.publisher.Mono.defer;
import static reactor.core.publisher.Mono.from;

import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.Updates;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;
import dev.witchvermillion.cardinal.profile.Profile;
import dev.witchvermillion.cardinal.profile.ProfileLoader;
import dev.witchvermillion.cardinal.profile.ProfileManager;
import dev.witchvermillion.cardinal.profile.ProfileRegistrar;
import dev.witchvermillion.cardinal.profile.ProfileRegistry;
import io.avaje.inject.BeanTypes;
import jakarta.inject.Singleton;
import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import org.jspecify.annotations.Nullable;
import org.redisson.api.RLocalCachedMapReactive;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.api.options.LocalCachedMapOptions;
import org.redisson.api.options.LocalCachedMapOptions.EvictionPolicy;
import org.redisson.api.options.LocalCachedMapOptions.ReconnectionStrategy;
import reactor.core.publisher.Mono;

@Singleton
@BeanTypes({
  ProfileRegistrar.class,
  ProfileLoader.class,
  ProfileRegistry.class,
  ProfileManager.class
})
final class ProfileManagerImpl implements ProfileManager {

  private static final int PROFILE_REDISSON_MAP_CACHE_SIZE = 512;

  private static final String PROFILE_MONGO_COLLECTION_NAME = "profiles",
      PROFILE_REDISSON_MAP_NAME = "profiles",
      PROFILE_ID_CANNOT_BE_NULL = "Profile ID cannot be null";

  private static final Duration PROFILE_REDISSON_MAP_MAX_IDLE_DURATION = Duration.ofMinutes(10);

  private final MongoCollection<ProfileImpl> profileMongoCollection;

  private final RLocalCachedMapReactive<UUID, ProfileImpl> profileRedissonMap;

  ProfileManagerImpl(
      final MongoDatabase mongoDatabase, final RedissonReactiveClient redissonReactiveClient) {
    this.profileMongoCollection =
        mongoDatabase.getCollection(PROFILE_MONGO_COLLECTION_NAME, ProfileImpl.class);

    this.profileRedissonMap =
        redissonReactiveClient.getLocalCachedMap(
            LocalCachedMapOptions.<UUID, ProfileImpl>name(PROFILE_REDISSON_MAP_NAME)
                .reconnectionStrategy(ReconnectionStrategy.CLEAR)
                .evictionPolicy(EvictionPolicy.LRU)
                .cacheSize(PROFILE_REDISSON_MAP_CACHE_SIZE)
                .maxIdle(PROFILE_REDISSON_MAP_MAX_IDLE_DURATION));
  }

  @Override
  public Mono<Profile> registerProfile(final UUID profileId) {
    return this.profileRedissonMap
        .get(requireNonNull(profileId, PROFILE_ID_CANNOT_BE_NULL))
        .switchIfEmpty(
            defer(
                () ->
                    from(this.profileMongoCollection.findOneAndUpdate(
                            eq(profileId),
                            Updates.setOnInsert("createdAt", Instant.now()),
                            new FindOneAndUpdateOptions()
                                .upsert(true)
                                .returnDocument(ReturnDocument.AFTER)))
                        .flatMap(
                            profile ->
                                this.profileRedissonMap
                                    .fastPut(profileId, profile)
                                    .thenReturn(profile))))
        .cast(Profile.class);
  }

  @Override
  public Mono<Profile> loadProfile(final UUID profileId) {
    return this.profileRedissonMap
        .get(requireNonNull(profileId, PROFILE_ID_CANNOT_BE_NULL))
        .switchIfEmpty(
            from(this.profileMongoCollection.find(eq(profileId)).first())
                .flatMap(
                    profile ->
                        this.profileRedissonMap.fastPut(profileId, profile).thenReturn(profile)))
        .cast(Profile.class);
  }

  @Override
  public @Nullable Profile profileOrNull(final UUID profileId) {
    return this.profileRedissonMap
        .getCachedMap()
        .get(requireNonNull(profileId, PROFILE_ID_CANNOT_BE_NULL));
  }

  @Override
  public Optional<Profile> profile(final UUID profileId) {
    return Optional.ofNullable(this.profileOrNull(profileId));
  }
}
