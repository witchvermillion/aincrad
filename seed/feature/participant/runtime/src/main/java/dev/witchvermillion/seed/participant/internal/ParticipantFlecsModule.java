package dev.witchvermillion.seed.participant.internal;

import dev.witchvermillion.seed.participant.Participant;
import io.github.elebras1.flecs.FlecsModule;
import io.github.elebras1.flecs.World;
import jakarta.inject.Singleton;

@Singleton
final class ParticipantFlecsModule implements FlecsModule {

  @Override
  public void initModule(final World world) {
    world.module(this);
    world.component(Participant.class);
  }
}
