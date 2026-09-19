package dev.witchvermillion.cardinal.profile.internal;

import jakarta.inject.Singleton;
import java.time.Duration;
import java.util.UUID;
import org.jspecify.annotations.Nullable;
import org.redisson.api.RLocalCachedMapReactive;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.api.options.LocalCachedMapOptions;
import reactor.core.publisher.Mono;

@Singleton
final class ProfileRedisCache {

  private static final int PROFILE_REDIS_LOCAL_CACHE_SIZE = 512;

  private static final String PROFILE_REDIS_CACHE_NAME = "profiles";

  private static final Duration PROFILE_REDIS_LOCAL_CACHE_MAX_IDLE_DURATION =
      Duration.ofMinutes(10);

  private final RLocalCachedMapReactive<UUID, ProfileImpl> profileRedisCache;

  ProfileRedisCache(final RedissonReactiveClient redissonReactiveClient) {
    this.profileRedisCache =
        redissonReactiveClient.getLocalCachedMap(
            LocalCachedMapOptions.<UUID, ProfileImpl>name(PROFILE_REDIS_CACHE_NAME)
                .reconnectionStrategy(LocalCachedMapOptions.ReconnectionStrategy.CLEAR)
                .evictionPolicy(LocalCachedMapOptions.EvictionPolicy.LRU)
                .cacheSize(PROFILE_REDIS_LOCAL_CACHE_SIZE)
                .maxIdle(PROFILE_REDIS_LOCAL_CACHE_MAX_IDLE_DURATION));
  }

  Mono<ProfileImpl> cacheProfile(final ProfileImpl profile) {
    return this.profileRedisCache.fastPut(profile.id(), profile).thenReturn(profile);
  }

  Mono<ProfileImpl> cachedProfile(final UUID profileId) {
    return this.profileRedisCache.get(profileId);
  }

  @Nullable ProfileImpl locallyCachedProfileOrNull(final UUID profileId) {
    return this.profileRedisCache.getCachedMap().get(profileId);
  }
}
