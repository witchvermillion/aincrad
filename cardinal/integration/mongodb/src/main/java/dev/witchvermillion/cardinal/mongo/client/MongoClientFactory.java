package dev.witchvermillion.cardinal.mongo.client;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import org.bson.UuidRepresentation;
import org.jspecify.annotations.NonNull;

@Factory
final class MongoClientFactory {

  private static final int MINIMUM_CONNECTION_POOL_SIZE = 6, MAXIMUM_CONNECTION_POOL_SIZE = 12;

  @Bean
  @NonNull MongoClient mongoClient() {
    return MongoClients.create(
        MongoClientSettings.builder()
            .applyConnectionString(new ConnectionString(System.getenv("MONGO_URI")))
            .applyToConnectionPoolSettings(
                builder ->
                    builder
                        .minSize(MINIMUM_CONNECTION_POOL_SIZE)
                        .maxSize(MAXIMUM_CONNECTION_POOL_SIZE))
            .uuidRepresentation(UuidRepresentation.STANDARD)
            .build());
  }
}
