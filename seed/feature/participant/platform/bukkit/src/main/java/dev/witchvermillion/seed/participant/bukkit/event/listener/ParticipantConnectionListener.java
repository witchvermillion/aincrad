package dev.witchvermillion.seed.participant.bukkit.event.listener;

import static org.bukkit.event.EventPriority.HIGHEST;
import static org.bukkit.event.EventPriority.LOWEST;

import jakarta.inject.Singleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jspecify.annotations.NullMarked;

@Singleton
@NullMarked
final class ParticipantConnectionListener implements Listener {

  @EventHandler(priority = LOWEST)
  void onPlayerJoin(final PlayerJoinEvent playerJoinEvent) {}

  @EventHandler(priority = HIGHEST)
  void onPlayerQuit(final PlayerQuitEvent playerQuitEvent) {}
}
