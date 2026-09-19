package dev.witchvermillion.seed.flecs;

import io.avaje.inject.PostConstruct;
import io.github.elebras1.flecs.FlecsModule;
import io.github.elebras1.flecs.World;
import jakarta.inject.Singleton;
import java.util.Set;

@Singleton
final class FlecsModuleImporter {

  private final World world;
  private final Set<FlecsModule> flecsModules;

  FlecsModuleImporter(final World world, final Set<FlecsModule> flecsModules) {
    this.world = world;
    this.flecsModules = flecsModules;
  }

  @PostConstruct
  void importFlecsModules() {
    for (final FlecsModule flecsModule : this.flecsModules) {
      this.world.importModule(flecsModule);
    }
  }
}
