package dev.witchvermillion.cardinal.profile;

import org.jetbrains.annotations.ApiStatus;

@ApiStatus.NonExtendable
public interface ProfileManager extends ProfileRegistrar, ProfileLoader, ProfileRegistry {}
