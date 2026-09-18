package dev.witchvermillion.cardinal.bukkit.annotation;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import org.bukkit.command.CommandSender;
import org.incendo.cloud.annotations.AnnotationParser;
import org.incendo.cloud.paper.LegacyPaperCommandManager;
import org.jspecify.annotations.NullMarked;

@Factory
final class AnnotationParserFactory {

  @Bean
  @NullMarked
  AnnotationParser<CommandSender> annotationParser(
      final LegacyPaperCommandManager<CommandSender> legacyPaperCommandManager) {
    return new AnnotationParser<>(legacyPaperCommandManager, CommandSender.class);
  }
}
