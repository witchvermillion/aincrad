package dev.witchvermillion.cardinal.profile.internal;

import jakarta.inject.Singleton;
import java.time.Duration;
import java.util.UUID;
import org.jspecify.annotations.Nullable;
import org.redisson.api.RLocalCachedMapReactive;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.api.options.LocalCachedMapOptions;
import org.redisson.api.options.LocalCachedMapOptions.CacheProvider;
import org.redisson.api.options.LocalCachedMapOptions.EvictionPolicy;
import org.redisson.api.options.LocalCachedMapOptions.ReconnectionStrategy;
import reactor.core.publisher.Mono;

@Singleton
final class ProfileRedisCache {

  private static final int LOCAL_CACHE_SIZE = 512;

  private static final String CACHE_NAME = "profiles";

  private static final Duration LOCAL_CACHE_MAX_IDLE_DURATION = Duration.ofMinutes(10);

  private final RLocalCachedMapReactive<UUID, ProfileImpl> profiles;

  ProfileRedisCache(final RedissonReactiveClient redissonReactiveClient) {
    this.profiles =
        redissonReactiveClient.getLocalCachedMap(
            LocalCachedMapOptions.<UUID, ProfileImpl>name(CACHE_NAME)
                .cacheSize(LOCAL_CACHE_SIZE)
                .maxIdle(LOCAL_CACHE_MAX_IDLE_DURATION)
                .evictionPolicy(EvictionPolicy.LRU)
                .cacheProvider(CacheProvider.CAFFEINE)
                .reconnectionStrategy(ReconnectionStrategy.CLEAR));
  }

  Mono<ProfileImpl> cacheProfile(final ProfileImpl profile) {
    return this.profiles.fastPut(profile.id(), profile).thenReturn(profile);
  }

  Mono<ProfileImpl> profile(final UUID profileId) {
    return this.profiles.get(profileId);
  }

  @Nullable ProfileImpl localProfileOrNull(final UUID profileId) {
    return this.profiles.getCachedMap().get(profileId);
  }
}
