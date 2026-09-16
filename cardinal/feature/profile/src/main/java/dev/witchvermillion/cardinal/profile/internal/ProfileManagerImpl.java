package dev.witchvermillion.cardinal.profile.internal;

import dev.witchvermillion.cardinal.profile.Profile;
import dev.witchvermillion.cardinal.profile.ProfileManager;
import dev.witchvermillion.cardinal.profile.loader.ProfileLoader;
import dev.witchvermillion.cardinal.profile.registrar.ProfileRegistrar;
import io.avaje.inject.BeanTypes;
import jakarta.inject.Singleton;
import java.util.UUID;
import reactor.core.publisher.Mono;

@Singleton
@BeanTypes({ProfileRegistrar.class, ProfileLoader.class, ProfileManager.class})
final class ProfileManagerImpl implements ProfileManager {

  @Override
  public Mono<Profile> registerProfileWithId(final UUID profileId) {
    return null;
  }

  @Override
  public Mono<Profile> loadProfileWithId(final UUID profileId) {
    return null;
  }
}
