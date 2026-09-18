package dev.witchvermillion.cardinal.bukkit.command;

import io.avaje.inject.Bean;
import io.avaje.inject.External;
import io.avaje.inject.Factory;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.incendo.cloud.execution.ExecutionCoordinator;
import org.incendo.cloud.paper.LegacyPaperCommandManager;
import org.jspecify.annotations.NullMarked;

@Factory
final class CommandManagerFactory {

  @Bean
  @NullMarked
  LegacyPaperCommandManager<CommandSender> legacyPaperCommandManager(
      final @External Plugin plugin) {
    return LegacyPaperCommandManager.createNative(plugin, ExecutionCoordinator.simpleCoordinator());
  }
}
