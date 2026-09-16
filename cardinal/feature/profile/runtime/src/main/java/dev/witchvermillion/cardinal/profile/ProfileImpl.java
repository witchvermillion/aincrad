package dev.witchvermillion.cardinal.profile;

import java.time.Instant;
import java.util.UUID;

record ProfileImpl(UUID id, Instant createdAt) implements Profile {}
