package dev.witchvermillion.seed.participant.entity.index.internal;

import dev.witchvermillion.seed.entity.index.EntityIndex;
import dev.witchvermillion.seed.participant.entity.index.ParticipantEntityIndex;
import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import java.util.UUID;
import org.jspecify.annotations.NullMarked;

@Factory
final class ParticipantEntityIndexFactory {

  @Bean
  @NullMarked
  @ParticipantEntityIndex
  EntityIndex<UUID> participantEntityIndex() {
    return new EntityIndex<>();
  }
}
