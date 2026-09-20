package dev.witchvermillion.cardinal.i18n;

import java.time.Instant;
import java.util.Locale;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.NonExtendable
public interface Translation {

  TranslationKey key();

  Locale locale();

  String template();

  Instant createdAt();
}
