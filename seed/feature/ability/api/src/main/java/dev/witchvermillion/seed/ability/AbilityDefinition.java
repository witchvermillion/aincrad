package dev.witchvermillion.seed.ability;

import org.jetbrains.annotations.ApiStatus;

@ApiStatus.NonExtendable
public interface AbilityDefinition {

  String id();

  String name();

  String description();
}
