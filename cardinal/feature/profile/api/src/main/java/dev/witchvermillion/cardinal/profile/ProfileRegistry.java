package dev.witchvermillion.cardinal.profile;

import java.util.Optional;
import java.util.UUID;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.Nullable;

@ApiStatus.NonExtendable
public interface ProfileRegistry {

  @Nullable Profile profileOrNull(final UUID profileId);

  Optional<Profile> profile(final UUID profileId);
}
