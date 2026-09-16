package dev.witchvermillion.cardinal.profile;

import java.time.Instant;
import java.util.UUID;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

@NullMarked
@ApiStatus.NonExtendable
public interface Profile {

  UUID id();

  Instant createdAt();
}
