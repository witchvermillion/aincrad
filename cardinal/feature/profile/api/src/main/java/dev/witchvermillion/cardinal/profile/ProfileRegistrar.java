package dev.witchvermillion.cardinal.profile;

import java.util.UUID;
import org.jetbrains.annotations.ApiStatus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ApiStatus.NonExtendable
public interface ProfileRegistrar {

  Mono<Profile> registerProfile(final UUID profileId);

  Flux<Profile> registeredProfiles(final Iterable<UUID> profileIds);
}
