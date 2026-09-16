package dev.witchvermillion.cardinal.profile.internal;

import dev.witchvermillion.cardinal.profile.Profile;

import java.time.Instant;
import java.util.UUID;

record ProfileImpl(UUID id, Instant createdAt) implements Profile {}
