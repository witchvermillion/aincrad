package dev.witchvermillion.cardinal.profile;

import io.avaje.inject.BeanTypes;
import jakarta.inject.Singleton;
import java.util.UUID;
import reactor.core.publisher.Mono;

@Singleton
@BeanTypes({ProfileRegistrar.class, ProfileLoader.class, ProfileManager.class})
final class ProfileManagerImpl implements ProfileManager {

  @Override
  public Mono<Profile> registerProfile(final UUID profileId) {
    return null;
  }

  @Override
  public Mono<Profile> loadProfile(final UUID profileId) {
    return null;
  }
}
