package dev.witchvermillion.cardinal.i18n.internal;

import dev.witchvermillion.cardinal.i18n.TranslationKey;
import dev.witchvermillion.cardinal.i18n.TranslationKeyLoader;
import dev.witchvermillion.cardinal.i18n.TranslationKeyLocalRegistry;
import dev.witchvermillion.cardinal.i18n.TranslationKeyManager;
import io.avaje.inject.BeanTypes;
import jakarta.inject.Singleton;
import java.util.Optional;
import org.jspecify.annotations.Nullable;
import reactor.core.publisher.Flux;

@Singleton
@BeanTypes({TranslationKeyLoader.class, TranslationKeyLocalRegistry.class})
final class TranslationKeyManagerImpl implements TranslationKeyManager {

  private final TranslationKeyMongoStore translationKeyMongoStore;
  private final TranslationKeyRedisCache translationKeyRedisCache;

  TranslationKeyManagerImpl(
      final TranslationKeyMongoStore translationKeyMongoStore,
      final TranslationKeyRedisCache translationKeyRedisCache) {
    this.translationKeyMongoStore = translationKeyMongoStore;
    this.translationKeyRedisCache = translationKeyRedisCache;
  }

  @Override
  public Flux<TranslationKey> loadTranslationKeys() {
    return null;
  }

  @Override
  public @Nullable TranslationKey translationKeyOrNull(final String translationKeyPath) {
    return null;
  }

  @Override
  public Optional<TranslationKey> translationKey(final String translationKeyPath) {
    return Optional.ofNullable(this.translationKeyOrNull(translationKeyPath));
  }
}
