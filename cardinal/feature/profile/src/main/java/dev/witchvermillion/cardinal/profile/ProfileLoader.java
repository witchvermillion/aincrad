package dev.witchvermillion.cardinal.profile;

import java.util.UUID;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;
import reactor.core.publisher.Mono;

@ApiStatus.NonExtendable
public interface ProfileLoader {

  @NullMarked
  Mono<Profile> loadProfileWithId(final UUID profileId);
}
