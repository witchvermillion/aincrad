package dev.witchvermillion.cardinal.profile.registrar;

import dev.witchvermillion.cardinal.profile.Profile;
import java.util.UUID;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;
import reactor.core.publisher.Mono;

@ApiStatus.NonExtendable
public interface ProfileRegistrar {

  @NullMarked
  Mono<Profile> registerProfileWithId(final UUID profileId);
}
