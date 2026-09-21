package dev.witchvermillion.seed.participant.internal;

import dev.witchvermillion.seed.participant.ParticipantEntityManager;
import dev.witchvermillion.seed.participant.ParticipantEntityRegistrar;
import dev.witchvermillion.seed.participant.ParticipantEntityRegistry;
import io.avaje.inject.BeanTypes;
import io.github.elebras1.flecs.Entity;
import io.github.elebras1.flecs.World;
import jakarta.inject.Singleton;
import java.util.Optional;
import java.util.UUID;
import org.jspecify.annotations.Nullable;

@Singleton
@BeanTypes({
  ParticipantEntityRegistrar.class,
  ParticipantEntityRegistry.class,
  ParticipantEntityManager.class
})
final class ParticipantEntityManagerImpl implements ParticipantEntityManager {

  private final World world;
  private final ParticipantEntityIndex participantEntityIndex;

  ParticipantEntityManagerImpl(
      final World world, final ParticipantEntityIndex participantEntityIndex) {
    this.world = world;
    this.participantEntityIndex = participantEntityIndex;
  }

  @Override
  public void registerParticipantEntity(
      final UUID participantId, final String participantUsername) {}

  @Override
  public void unregisterParticipantEntity(final UUID participantId) {}

  @Override
  public @Nullable Entity participantEntityOrNull(final UUID participantId) {
    return null;
  }

  @Override
  public Optional<Entity> participantEntity(final UUID participantId) {
    return Optional.ofNullable(this.participantEntityOrNull(participantId));
  }
}
