package dev.witchvermillion.cardinal.i18n.internal;

import jakarta.inject.Singleton;
import java.time.Duration;
import java.util.Locale;
import org.jspecify.annotations.Nullable;
import org.redisson.api.RLocalCachedMapReactive;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.api.options.LocalCachedMapOptions;
import org.redisson.api.options.LocalCachedMapOptions.CacheProvider;
import org.redisson.api.options.LocalCachedMapOptions.EvictionPolicy;
import org.redisson.api.options.LocalCachedMapOptions.ReconnectionStrategy;
import reactor.core.publisher.Mono;

@Singleton
final class TranslationRedisCache {

  private static final int LOCAL_CACHE_SIZE = 2048;

  private static final String CACHE_NAME = "translations";

  private static final Duration LOCAL_CACHE_MAX_IDLE_DURATION = Duration.ofMinutes(25);

  private static String translationEntryKey(final String translationKey, final Locale locale) {
    return "%s:%s".formatted(translationKey, locale.toLanguageTag());
  }

  private final RLocalCachedMapReactive<String, TranslationImpl> translations;

  TranslationRedisCache(final RedissonReactiveClient redissonReactiveClient) {
    this.translations =
        redissonReactiveClient.getLocalCachedMap(
            LocalCachedMapOptions.<String, TranslationImpl>name(CACHE_NAME)
                .cacheSize(LOCAL_CACHE_SIZE)
                .maxIdle(LOCAL_CACHE_MAX_IDLE_DURATION)
                .evictionPolicy(EvictionPolicy.LRU)
                .cacheProvider(CacheProvider.CAFFEINE)
                .reconnectionStrategy(ReconnectionStrategy.CLEAR));
  }

  Mono<TranslationImpl> cacheTranslation(final TranslationImpl translation) {
    return this.translations
        .fastPut(translationEntryKey(translation.key(), translation.locale()), translation)
        .thenReturn(translation);
  }

  Mono<TranslationImpl> translation(final String translationKey, final Locale locale) {
    return this.translations.get(translationEntryKey(translationKey, locale));
  }

  @Nullable TranslationImpl localTranslationOrNull(
      final String translationKey, final Locale locale) {
    return this.translations.getCachedMap().get(translationEntryKey(translationKey, locale));
  }
}
