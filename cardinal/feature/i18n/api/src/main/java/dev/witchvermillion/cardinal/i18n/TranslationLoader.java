package dev.witchvermillion.cardinal.i18n;

import org.jetbrains.annotations.ApiStatus;
import reactor.core.publisher.Mono;

import java.util.Locale;

@ApiStatus.NonExtendable
public interface TranslationLoader {

  Mono<Translation> loadTranslation(final String translationKey, final Locale locale);
}
