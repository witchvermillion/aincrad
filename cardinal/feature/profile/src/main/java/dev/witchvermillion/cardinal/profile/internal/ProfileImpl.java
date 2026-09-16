package dev.witchvermillion.cardinal.profile.internal;

import java.time.Instant;
import java.util.UUID;
import org.bson.codecs.pojo.annotations.BsonId;

record ProfileImpl(@BsonId UUID id, Instant createdAt) {}
