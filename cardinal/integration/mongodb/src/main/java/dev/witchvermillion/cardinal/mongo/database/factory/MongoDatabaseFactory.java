package dev.witchvermillion.cardinal.mongo.database.factory;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoDatabase;
import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import org.jspecify.annotations.NullMarked;

@Factory
final class MongoDatabaseFactory {

  @Bean
  @NullMarked
  MongoDatabase mongoDatabase(final MongoClient mongoClient) {
    return mongoClient.getDatabase(System.getenv("MONGO_DATABASE_NAME"));
  }
}
