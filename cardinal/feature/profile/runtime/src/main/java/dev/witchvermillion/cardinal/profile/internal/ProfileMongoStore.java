package dev.witchvermillion.cardinal.profile.internal;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromCodecs;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import static reactor.core.publisher.Mono.defer;
import static reactor.core.publisher.Mono.from;

import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.Updates;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;
import jakarta.inject.Singleton;
import java.time.Instant;
import java.util.UUID;
import reactor.core.publisher.Mono;

@Singleton
final class ProfileMongoStore {

  private static final String PROFILE_MONGO_COLLECTION_NAME = "profiles";

  private final MongoCollection<ProfileImpl> profileMongoCollection;

  ProfileMongoStore(final MongoDatabase mongoDatabase) {
    this.profileMongoCollection =
        mongoDatabase
            .getCollection(PROFILE_MONGO_COLLECTION_NAME, ProfileImpl.class)
            .withCodecRegistry(
                fromRegistries(fromCodecs(new ProfileCodec()), mongoDatabase.getCodecRegistry()));
  }

  Mono<ProfileImpl> findOrInsertProfile(final UUID profileId) {
    return defer(
        () ->
            from(
                this.profileMongoCollection.findOneAndUpdate(
                    eq(profileId),
                    Updates.setOnInsert(ProfileCodec.CREATED_AT_FIELD_NAME, Instant.now()),
                    new FindOneAndUpdateOptions()
                        .upsert(true)
                        .returnDocument(ReturnDocument.AFTER))));
  }

  Mono<ProfileImpl> findProfile(final UUID profileId) {
    return from(this.profileMongoCollection.find(eq(profileId)).first());
  }
}
