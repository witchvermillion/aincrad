package dev.witchvermillion.seed.participant.internal;

import dev.witchvermillion.seed.entity.index.EntityIndex;
import dev.witchvermillion.seed.participant.Participant;
import dev.witchvermillion.seed.participant.ParticipantEntityRegistry;
import dev.witchvermillion.seed.participant.ParticipantManager;
import dev.witchvermillion.seed.participant.ParticipantRegistrar;
import io.avaje.inject.BeanTypes;
import io.github.elebras1.flecs.Entity;
import io.github.elebras1.flecs.World;
import jakarta.inject.Singleton;
import java.util.Optional;
import java.util.UUID;
import org.jspecify.annotations.Nullable;

@Singleton
@BeanTypes({ParticipantRegistrar.class, ParticipantEntityRegistry.class, ParticipantManager.class})
final class ParticipantManagerImpl implements ParticipantManager {

  private final World world;
  private final EntityIndex<UUID> participantEntityIndex;

  ParticipantManagerImpl(
      final World world, final @ParticipantEntityIndex EntityIndex<UUID> participantEntityIndex) {
    this.world = world;
    this.participantEntityIndex = participantEntityIndex;
  }

  @Override
  public void registerParticipant(final UUID participantId, final String participantUsername) {
    this.participantEntityIndex
        .entity(participantId)
        .ifPresentOrElse(
            participantEntity -> {
              if (!participantUsername.equals(participantEntity.name())) {
                participantEntity.name(participantUsername);
              }
            },
            () -> {
              final Entity participantEntity =
                  this.world
                      .obtainEntity(this.world.entity())
                      .name(participantUsername)
                      .add(Participant.class);

              this.participantEntityIndex.putEntity(participantId, participantEntity);
            });
  }

  @Override
  public void unregisterParticipant(final UUID participantId) {
    final Entity participantEntity = this.participantEntityIndex.removeEntityByKey(participantId);
    if (participantEntity != null) {
      participantEntity.destruct();
    }
  }

  @Override
  public @Nullable Entity participantEntityOrNull(final UUID participantId) {
    return this.participantEntityIndex.entityOrNull(participantId);
  }

  @Override
  public Optional<Entity> participantEntity(final UUID participantId) {
    return this.participantEntityIndex.entity(participantId);
  }
}
