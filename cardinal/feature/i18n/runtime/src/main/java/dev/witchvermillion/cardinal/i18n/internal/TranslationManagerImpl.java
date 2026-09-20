package dev.witchvermillion.cardinal.i18n.internal;

import dev.witchvermillion.cardinal.i18n.TranslationLoader;
import dev.witchvermillion.cardinal.i18n.TranslationLocalRegistry;
import dev.witchvermillion.cardinal.i18n.TranslationManager;
import io.avaje.inject.BeanTypes;
import jakarta.inject.Singleton;

@Singleton
@BeanTypes({TranslationLoader.class, TranslationLocalRegistry.class, TranslationManager.class})
final class TranslationManagerImpl implements TranslationManager {}
