package dev.witchvermillion.cardinal.bukkit.logger;

import io.avaje.inject.Bean;
import io.avaje.inject.External;
import io.avaje.inject.Factory;
import java.util.logging.Logger;
import org.bukkit.plugin.Plugin;
import org.jspecify.annotations.NullMarked;

@Factory
final class LoggerFactory {

  @Bean
  @NullMarked
  Logger logger(final @External Plugin plugin) {
    return plugin.getLogger();
  }
}
