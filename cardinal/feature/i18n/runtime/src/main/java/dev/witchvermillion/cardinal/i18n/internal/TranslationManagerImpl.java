package dev.witchvermillion.cardinal.i18n.internal;

import dev.witchvermillion.cardinal.i18n.Translation;
import dev.witchvermillion.cardinal.i18n.TranslationLoader;
import dev.witchvermillion.cardinal.i18n.TranslationLocalRegistry;
import dev.witchvermillion.cardinal.i18n.TranslationManager;
import io.avaje.inject.BeanTypes;
import jakarta.inject.Singleton;
import java.util.Locale;
import java.util.Optional;
import org.jspecify.annotations.Nullable;

@Singleton
@BeanTypes({TranslationLoader.class, TranslationLocalRegistry.class, TranslationManager.class})
final class TranslationManagerImpl implements TranslationManager {

  @Override
  public @Nullable Translation translationOrNull(final String translationKey, final Locale locale) {
    return null;
  }

  @Override
  public Optional<Translation> translation(final String translationKey, final Locale locale) {
    return Optional.ofNullable(this.translationOrNull(translationKey, locale));
  }
}
