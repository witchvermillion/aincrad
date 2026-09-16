package dev.witchvermillion.cardinal.profile.loader;

import dev.witchvermillion.cardinal.profile.Profile;
import java.util.UUID;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;
import reactor.core.publisher.Mono;

@ApiStatus.NonExtendable
public interface ProfileLoader {

  @NullMarked
  Mono<Profile> loadProfileWithId(final UUID profileId);
}
