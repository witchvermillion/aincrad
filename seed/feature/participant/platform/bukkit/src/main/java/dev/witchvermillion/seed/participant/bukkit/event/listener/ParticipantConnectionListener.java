package dev.witchvermillion.seed.participant.bukkit.event.listener;

import static org.bukkit.event.EventPriority.LOWEST;

import dev.witchvermillion.seed.entity.index.EntityIndex;
import dev.witchvermillion.seed.participant.entity.index.ParticipantEntityIndex;
import io.github.elebras1.flecs.Entity;
import io.github.elebras1.flecs.World;
import jakarta.inject.Singleton;
import java.util.UUID;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jspecify.annotations.NullMarked;

@Singleton
@NullMarked
final class ParticipantConnectionListener implements Listener {

  private final World world;
  private final EntityIndex<UUID> participantEntityIndex;

  ParticipantConnectionListener(
      final World world, final @ParticipantEntityIndex EntityIndex<UUID> participantEntityIndex) {
    this.world = world;
    this.participantEntityIndex = participantEntityIndex;
  }

  @EventHandler(priority = LOWEST)
  void onPlayerJoin(final PlayerJoinEvent playerJoinEvent) {
    final Player player = playerJoinEvent.getPlayer();

    final UUID playerId = player.getUniqueId();

    final String playerName = player.getName();

    final Entity participantEntity = this.participantEntityIndex.entityOrNull(playerId);
    if (participantEntity == null) {
      this.participantEntityIndex.addEntity(
          playerId, this.world.obtainEntity(this.world.entity(playerName)));
      return;
    }

    if (!playerName.equals(participantEntity.name())) {
      participantEntity.name(playerName);
    }
  }
}
