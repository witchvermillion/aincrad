package dev.witchvermillion.seed.participant.bukkit.event.listener;

import dev.witchvermillion.seed.participant.ParticipantEntityRegistrar;
import jakarta.inject.Singleton;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jspecify.annotations.NullMarked;

@Singleton
@NullMarked
final class ParticipantConnectionEventListener implements Listener {

  private final ParticipantEntityRegistrar participantEntityRegistrar;

  ParticipantConnectionEventListener(final ParticipantEntityRegistrar participantEntityRegistrar) {
    this.participantEntityRegistrar = participantEntityRegistrar;
  }

  @EventHandler(priority = EventPriority.LOWEST)
  void onPlayerJoin(final PlayerJoinEvent playerJoinEvent) {
    final Player player = playerJoinEvent.getPlayer();
    this.participantEntityRegistrar.registerParticipantEntity(
        player.getUniqueId(), player.getName());
  }
}
