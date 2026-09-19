package dev.witchvermillion.seed.participant.event.listener;

import static org.bukkit.event.EventPriority.HIGHEST;

import dev.witchvermillion.seed.entity.index.EntityIndex;
import dev.witchvermillion.seed.participant.entity.index.ParticipantEntityIndex;
import jakarta.inject.Singleton;
import java.util.UUID;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jspecify.annotations.NullMarked;

@Singleton
@NullMarked
final class ParticipantConnectionListener implements Listener {

  private final EntityIndex<UUID> participantEntityIndex;

  ParticipantConnectionListener(
      final @ParticipantEntityIndex EntityIndex<UUID> participantEntityIndex) {
    this.participantEntityIndex = participantEntityIndex;
  }

  @EventHandler(priority = HIGHEST)
  void onPlayerJoin(final PlayerJoinEvent playerJoinEvent) {}

  @EventHandler(priority = HIGHEST)
  void onPlayerQuit(final PlayerQuitEvent playerQuitEvent) {}
}
