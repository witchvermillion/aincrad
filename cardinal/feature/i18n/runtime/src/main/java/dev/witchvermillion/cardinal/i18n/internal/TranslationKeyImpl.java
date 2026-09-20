package dev.witchvermillion.cardinal.i18n.internal;

import dev.witchvermillion.cardinal.i18n.TranslationKey;
import java.time.Instant;

record TranslationKeyImpl(String path, Instant createdAt) implements TranslationKey {}
