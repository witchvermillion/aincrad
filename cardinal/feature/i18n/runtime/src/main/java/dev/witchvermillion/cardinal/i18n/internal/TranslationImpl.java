package dev.witchvermillion.cardinal.i18n.internal;

import dev.witchvermillion.cardinal.i18n.Translation;
import dev.witchvermillion.cardinal.i18n.TranslationKey;
import java.time.Instant;
import java.util.Locale;

record TranslationImpl(TranslationKey key, Locale locale, Instant createdAt)
    implements Translation {}
