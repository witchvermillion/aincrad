package dev.witchvermillion.cardinal.i18n;

import java.time.Instant;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.NonExtendable
public interface TranslationKey {

  String namespace();

  String name();

  Instant createdAt();
}
