package dev.witchvermillion.cardinal.profile;

import java.util.UUID;
import org.jetbrains.annotations.ApiStatus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ApiStatus.NonExtendable
public interface ProfileLoader {

  Mono<Profile> loadProfile(final UUID profileId);

  Flux<Profile> loadProfiles(final Iterable<UUID> profileIds);
}
