package dev.witchvermillion.cardinal.i18n.internal;

import dev.witchvermillion.cardinal.i18n.Translation;
import java.time.Instant;
import java.util.Locale;

record TranslationImpl(String key, Locale locale, String template, Instant createdAt)
    implements Translation {}
