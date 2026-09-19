package dev.witchvermillion.seed.world;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import io.github.elebras1.flecs.World;
import org.jspecify.annotations.NonNull;

@Factory
final class WorldFactory {

  @Bean(destroyMethod = "destroy")
  @NonNull World world() {
    return new World();
  }
}
