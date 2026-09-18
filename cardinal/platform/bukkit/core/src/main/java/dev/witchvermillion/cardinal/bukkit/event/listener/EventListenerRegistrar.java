package dev.witchvermillion.cardinal.bukkit.event.listener;

import io.avaje.inject.External;
import io.avaje.inject.PostConstruct;
import jakarta.inject.Singleton;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

@Singleton
final class EventListenerRegistrar {

  private final Plugin plugin;
  private final Set<Listener> listeners;

  EventListenerRegistrar(final @External Plugin plugin, final Set<Listener> listeners) {
    this.plugin = plugin;
    this.listeners = listeners;
  }

  @PostConstruct
  void registerListeners() {
    for (final Listener listener : this.listeners) {
      Bukkit.getPluginManager().registerEvents(listener, this.plugin);
    }
  }
}
