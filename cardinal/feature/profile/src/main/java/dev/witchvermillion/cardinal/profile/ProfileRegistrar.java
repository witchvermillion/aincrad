package dev.witchvermillion.cardinal.profile;

import java.util.UUID;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;
import reactor.core.publisher.Mono;

@ApiStatus.NonExtendable
public interface ProfileRegistrar {

  @NullMarked
  Mono<Profile> registerProfileWithId(final UUID profileId);
}
