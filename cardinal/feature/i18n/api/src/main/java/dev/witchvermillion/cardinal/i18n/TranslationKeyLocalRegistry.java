package dev.witchvermillion.cardinal.i18n;

import java.util.Optional;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.Nullable;

@ApiStatus.NonExtendable
public interface TranslationKeyLocalRegistry {

  @Nullable TranslationKey translationKeyOrNull(final String translationKeyPath);

  Optional<TranslationKey> translationKey(final String translationKeyPath);
}
