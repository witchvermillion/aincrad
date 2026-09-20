package dev.witchvermillion.cardinal.i18n.internal;

import dev.witchvermillion.cardinal.i18n.TranslationKey;
import java.time.Instant;

record TranslationKeyImpl(String namespace, String name, Instant createdAt)
    implements TranslationKey {

  @Override
  public String toString() {
    return "%s:%s".formatted(this.namespace, this.name);
  }
}
