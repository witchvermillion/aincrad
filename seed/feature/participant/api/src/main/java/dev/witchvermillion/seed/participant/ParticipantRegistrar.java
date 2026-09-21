package dev.witchvermillion.seed.participant;

import java.util.UUID;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.NonExtendable
public interface ParticipantRegistrar {

  void registerParticipant(final UUID participantId, final String participantUsername);

  void unregisterParticipant(final UUID participantId);
}
