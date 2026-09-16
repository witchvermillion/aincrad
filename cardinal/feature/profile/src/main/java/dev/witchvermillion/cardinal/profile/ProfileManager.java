package dev.witchvermillion.cardinal.profile;

import dev.witchvermillion.cardinal.profile.loader.ProfileLoader;
import dev.witchvermillion.cardinal.profile.registrar.ProfileRegistrar;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.NonExtendable
public interface ProfileManager extends ProfileRegistrar, ProfileLoader {}
