package dev.witchvermillion.cardinal.profile.internal;

import dev.witchvermillion.cardinal.profile.Profile;
import dev.witchvermillion.cardinal.profile.ProfileLoader;
import dev.witchvermillion.cardinal.profile.ProfileManager;
import dev.witchvermillion.cardinal.profile.ProfileRegistrar;
import io.avaje.inject.BeanTypes;
import jakarta.inject.Singleton;
import java.util.UUID;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Singleton
@BeanTypes({ProfileRegistrar.class, ProfileLoader.class, ProfileManager.class})
final class ProfileManagerImpl implements ProfileManager {

  @Override
  public Mono<Profile> registerProfile(final UUID profileId) {
    return null;
  }

  @Override
  public Flux<Profile> registerProfiles(final Iterable<UUID> profileIds) {
    return null;
  }

  @Override
  public Mono<Profile> loadProfile(final UUID profileId) {
    return null;
  }

  @Override
  public Flux<Profile> loadProfiles(final Iterable<UUID> profileIds) {
    return null;
  }
}
