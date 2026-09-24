package dev.witchvermillion.seed.ability.internal;

import dev.witchvermillion.seed.ability.AbilityDefinition;
import dev.witchvermillion.seed.ability.AbilityDefinitionManager;
import dev.witchvermillion.seed.ability.AbilityDefinitionRegistry;
import io.avaje.inject.BeanTypes;
import jakarta.inject.Singleton;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

@Singleton
@BeanTypes({AbilityDefinitionRegistry.class, AbilityDefinitionManager.class})
@NullMarked
final class AbilityDefinitionManagerImpl implements AbilityDefinitionManager {

  private final Map<String, AbilityDefinition> abilityDefinitions = new HashMap<>();

  @Override
  public @Nullable AbilityDefinition abilityDefinitionOrNull(final String abilityId) {
    return null;
  }

  @Override
  public Optional<AbilityDefinition> abilityDefinition(final String abilityId) {
    return Optional.ofNullable(this.abilityDefinitionOrNull(abilityId));
  }
}
