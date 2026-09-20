package dev.witchvermillion.cardinal.i18n.internal;

import static java.util.Objects.requireNonNull;

import dev.witchvermillion.cardinal.i18n.Translation;
import dev.witchvermillion.cardinal.i18n.TranslationLoader;
import dev.witchvermillion.cardinal.i18n.TranslationLocalRegistry;
import dev.witchvermillion.cardinal.i18n.TranslationManager;
import io.avaje.inject.BeanTypes;
import jakarta.inject.Singleton;
import java.util.Locale;
import java.util.Optional;
import org.jspecify.annotations.Nullable;
import reactor.core.publisher.Mono;

@Singleton
@BeanTypes({TranslationLoader.class, TranslationLocalRegistry.class, TranslationManager.class})
final class TranslationManagerImpl implements TranslationManager {

  private static final String TRANSLATION_KEY_CANNOT_BE_NULL = "Translation key cannot be null",
      LOCALE_CANNOT_BE_NULL = "Locale cannot be null";

  private final TranslationMongoStore translationMongoStore;
  private final TranslationRedisCache translationRedisCache;

  TranslationManagerImpl(
      final TranslationMongoStore translationMongoStore,
      final TranslationRedisCache translationRedisCache) {
    this.translationMongoStore = translationMongoStore;
    this.translationRedisCache = translationRedisCache;
  }

  @Override
  public Mono<Translation> loadTranslation(final String translationKey, final Locale locale) {
    return this.translationRedisCache
        .translation(
            requireNonNull(translationKey, TRANSLATION_KEY_CANNOT_BE_NULL),
            requireNonNull(locale, LOCALE_CANNOT_BE_NULL))
        .switchIfEmpty(
            this.translationMongoStore
                .findTranslation(translationKey, locale)
                .flatMap(this.translationRedisCache::cacheTranslation))
        .cast(Translation.class);
  }

  @Override
  public @Nullable Translation translationOrNull(final String translationKey, final Locale locale) {
    return this.translationRedisCache.localTranslationOrNull(
        requireNonNull(translationKey, TRANSLATION_KEY_CANNOT_BE_NULL),
        requireNonNull(locale, LOCALE_CANNOT_BE_NULL));
  }

  @Override
  public Optional<Translation> translation(final String translationKey, final Locale locale) {
    return Optional.ofNullable(this.translationOrNull(translationKey, locale));
  }
}
