package dev.witchvermillion.cardinal.i18n;

import org.jetbrains.annotations.ApiStatus;
import reactor.core.publisher.Flux;

@ApiStatus.NonExtendable
public interface TranslationKeyLoader {

  Flux<TranslationKey> loadTranslationKeys();
}
