package dev.witchvermillion.cardinal.i18n.internal;

import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;
import jakarta.inject.Singleton;

@Singleton
final class TranslationMongoStore {

  private static final String MONGO_COLLECTION_NAME = "translations";

  private final MongoCollection<TranslationImpl> translationMongoCollection;

  TranslationMongoStore(final MongoDatabase mongoDatabase) {
    this.translationMongoCollection =
        mongoDatabase.getCollection(MONGO_COLLECTION_NAME, TranslationImpl.class);
  }
}
