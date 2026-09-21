package dev.witchvermillion.seed.participant;

import io.github.elebras1.flecs.Entity;
import java.util.Optional;
import java.util.UUID;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.Nullable;

@ApiStatus.NonExtendable
public interface ParticipantRegistry {

  @Nullable Entity participantEntityOrNull(final UUID participantId);

  Optional<Entity> participantEntity(final UUID participantId);
}
