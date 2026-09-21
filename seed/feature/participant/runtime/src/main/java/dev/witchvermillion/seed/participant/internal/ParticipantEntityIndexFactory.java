package dev.witchvermillion.seed.participant.internal;

import dev.witchvermillion.seed.entity.index.EntityIndex;
import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import java.util.UUID;

@Factory
final class ParticipantEntityIndexFactory {

  @Bean
  @ParticipantEntityIndex
  EntityIndex<UUID> participantEntityIndex() {
    return new EntityIndex<>();
  }
}
