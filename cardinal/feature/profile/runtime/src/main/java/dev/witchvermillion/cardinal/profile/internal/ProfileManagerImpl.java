package dev.witchvermillion.cardinal.profile.internal;

import static java.util.Objects.requireNonNull;

import dev.witchvermillion.cardinal.profile.Profile;
import dev.witchvermillion.cardinal.profile.ProfileLoader;
import dev.witchvermillion.cardinal.profile.ProfileManager;
import dev.witchvermillion.cardinal.profile.ProfileRegistrar;
import dev.witchvermillion.cardinal.profile.ProfileRegistry;
import io.avaje.inject.BeanTypes;
import jakarta.inject.Singleton;
import java.util.Optional;
import java.util.UUID;
import org.jspecify.annotations.Nullable;
import reactor.core.publisher.Mono;

@Singleton
@BeanTypes({
  ProfileRegistrar.class,
  ProfileLoader.class,
  ProfileRegistry.class,
  ProfileManager.class
})
final class ProfileManagerImpl implements ProfileManager {

  private static final String PROFILE_ID_CANNOT_BE_NULL = "Profile ID cannot be null";

  private final ProfileMongoStore profileMongoStore;
  private final ProfileRedisCache profileRedisCache;

  ProfileManagerImpl(
      final ProfileMongoStore profileMongoStore, final ProfileRedisCache profileRedisCache) {
    this.profileMongoStore = profileMongoStore;
    this.profileRedisCache = profileRedisCache;
  }

  @Override
  public Mono<Profile> registerProfile(final UUID profileId) {
    return this.profileRedisCache
        .cachedProfile(requireNonNull(profileId, PROFILE_ID_CANNOT_BE_NULL))
        .switchIfEmpty(
            this.profileMongoStore
                .findOrInsertProfile(profileId)
                .flatMap(this.profileRedisCache::cacheProfile))
        .cast(Profile.class);
  }

  @Override
  public Mono<Profile> loadProfile(final UUID profileId) {
    return this.profileRedisCache
        .cachedProfile(requireNonNull(profileId, PROFILE_ID_CANNOT_BE_NULL))
        .switchIfEmpty(
            this.profileMongoStore
                .findProfile(profileId)
                .flatMap(this.profileRedisCache::cacheProfile))
        .cast(Profile.class);
  }

  @Override
  public @Nullable Profile profileOrNull(final UUID profileId) {
    return this.profileRedisCache.locallyCachedProfileOrNull(
        requireNonNull(profileId, PROFILE_ID_CANNOT_BE_NULL));
  }

  @Override
  public Optional<Profile> profile(final UUID profileId) {
    return Optional.ofNullable(this.profileOrNull(profileId));
  }
}
