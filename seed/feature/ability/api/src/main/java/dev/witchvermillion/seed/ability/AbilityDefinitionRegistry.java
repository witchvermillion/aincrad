package dev.witchvermillion.seed.ability;

import java.util.Optional;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.Nullable;

@ApiStatus.NonExtendable
public interface AbilityDefinitionRegistry {

  @Nullable AbilityDefinition abilityDefinitionOrNull(final String abilityId);

  Optional<AbilityDefinition> abilityDefinition(final String abilityId);
}
