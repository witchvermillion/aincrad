package dev.witchvermillion.seed.participant.internal.flecs.module;

import dev.witchvermillion.seed.participant.component.Participant;
import dev.witchvermillion.seed.participant.component.Username;
import io.github.elebras1.flecs.FlecsModule;
import io.github.elebras1.flecs.World;
import jakarta.inject.Singleton;
import org.jspecify.annotations.NonNull;

@Singleton
final class ParticipantFlecsModule implements FlecsModule {

  @Override
  public void initModule(final @NonNull World world) {
    world.module(this);

    world.component(Username.class);
    world.component(Participant.class);
  }
}
