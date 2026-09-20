package dev.witchvermillion.cardinal.i18n;

import java.util.Locale;
import java.util.Optional;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.Nullable;

@ApiStatus.NonExtendable
public interface TranslationLocalRegistry {

  @Nullable Translation translationOrNull(final String translationKey, final Locale locale);

  Optional<Translation> translation(final String translationKey, final Locale locale);
}
