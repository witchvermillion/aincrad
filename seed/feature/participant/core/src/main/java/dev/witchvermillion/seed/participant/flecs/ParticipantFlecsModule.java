package dev.witchvermillion.seed.participant.flecs;

import dev.witchvermillion.seed.participant.component.Disconnected;
import dev.witchvermillion.seed.participant.component.Participant;
import io.github.elebras1.flecs.FlecsModule;
import io.github.elebras1.flecs.World;
import org.jspecify.annotations.NonNull;

final class ParticipantFlecsModule implements FlecsModule {

  @Override
  public void initModule(final @NonNull World world) {
    world.component(Participant.class);
    world.component(Disconnected.class);
  }
}
