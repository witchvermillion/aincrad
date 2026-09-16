package dev.witchvermillion.cardinal.profile;

import java.util.UUID;
import org.jetbrains.annotations.ApiStatus;
import reactor.core.publisher.Mono;

@ApiStatus.NonExtendable
public interface ProfileRegistrar {

  Mono<Profile> registerProfile(final UUID profileId);
}
