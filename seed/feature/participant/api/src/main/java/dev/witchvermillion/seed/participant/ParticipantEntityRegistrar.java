package dev.witchvermillion.seed.participant;

import java.util.UUID;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.NonExtendable
public interface ParticipantEntityRegistrar {

  void registerParticipantEntity(final UUID participantId, final String participantUsername);

  void unregisterParticipantEntity(final UUID participantId);
}
