package dev.witchvermillion.seed.participant.internal;

import static java.util.Objects.requireNonNull;

import dev.witchvermillion.seed.participant.ParticipantEntityManager;
import dev.witchvermillion.seed.participant.ParticipantEntityRegistrar;
import dev.witchvermillion.seed.participant.ParticipantEntityRegistry;
import dev.witchvermillion.seed.participant.component.Participant;
import dev.witchvermillion.seed.participant.component.Username;
import dev.witchvermillion.seed.participant.component.UsernameView;
import io.avaje.inject.BeanTypes;
import io.github.elebras1.flecs.Entity;
import io.github.elebras1.flecs.World;
import jakarta.inject.Singleton;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

@Singleton
@BeanTypes({
  ParticipantEntityRegistrar.class,
  ParticipantEntityRegistry.class,
  ParticipantEntityManager.class
})
@NullMarked
final class ParticipantEntityManagerImpl implements ParticipantEntityManager {

  private static final String PARTICIPANT_ID_CANNOT_BE_NULL = "Participant ID cannot be null";

  private final World world;
  private final Map<UUID, Entity> participants;

  ParticipantEntityManagerImpl(final World world) {
    this.world = world;
    this.participants = new HashMap<>();
  }

  @Override
  public void registerParticipantEntity(
      final UUID participantId, final String participantUsername) {
    requireNonNull(participantId, PARTICIPANT_ID_CANNOT_BE_NULL);
    requireNonNull(participantUsername, "Participant username cannot be null");

    final Entity participantEntity = this.participants.get(participantId);
    if (participantEntity == null) {
      this.participants.put(participantId, this.createParticipantEntity(participantUsername));
      return;
    }

    final UsernameView usernameView = participantEntity.getMutView(Username.class);
    if (!participantUsername.equals(usernameView.username())) {
      usernameView.username(participantUsername);
    }
  }

  private Entity createParticipantEntity(final String participantUsername) {
    return this.world
        .obtainEntity(this.world.entity())
        .add(Participant.class)
        .set(new Username(participantUsername));
  }

  @Override
  public void unregisterParticipantEntity(final UUID participantId) {
    final Entity participantEntity =
        this.participants.remove(requireNonNull(participantId, PARTICIPANT_ID_CANNOT_BE_NULL));
    if (participantEntity != null) {
      participantEntity.destruct();
    }
  }

  @Override
  public @Nullable Entity participantEntityOrNull(final UUID participantId) {
    return this.participants.get(requireNonNull(participantId, PARTICIPANT_ID_CANNOT_BE_NULL));
  }

  @Override
  public Optional<Entity> participantEntity(final UUID participantId) {
    return Optional.ofNullable(this.participantEntityOrNull(participantId));
  }
}
