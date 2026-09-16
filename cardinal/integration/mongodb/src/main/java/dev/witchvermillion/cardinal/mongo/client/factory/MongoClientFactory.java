package dev.witchvermillion.cardinal.mongo.client.factory;

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

  @Bean
  @NonNull MongoClient mongoClient() {
    return MongoClients.create(
        MongoClientSettings.builder()
            .applyConnectionString(new ConnectionString(System.getenv("MONGO_URI")))
            .applyToConnectionPoolSettings(builder -> builder.minSize(6).maxSize(12))
            .uuidRepresentation(UuidRepresentation.STANDARD)
            .build());
  }
}
