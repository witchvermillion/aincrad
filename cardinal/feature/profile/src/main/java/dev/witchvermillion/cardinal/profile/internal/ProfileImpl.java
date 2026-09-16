package dev.witchvermillion.cardinal.profile.internal;

import dev.witchvermillion.cardinal.profile.Profile;
import java.time.Instant;
import java.util.UUID;
import org.bson.codecs.pojo.annotations.BsonId;

record ProfileImpl(@BsonId UUID id, Instant createdAt) implements Profile {}
