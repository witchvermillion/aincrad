package dev.witchvermillion.cardinal.i18n.internal;

import static org.bson.codecs.configuration.CodecRegistries.fromCodecs;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;
import jakarta.inject.Singleton;

@Singleton
final class TranslationKeyMongoStore {

  private static final String MONGO_COLLECTION_NAME = "translation_keys";

  private final MongoCollection<TranslationKeyImpl> translationKeyMongoCollection;

  TranslationKeyMongoStore(final MongoDatabase mongoDatabase) {
    this.translationKeyMongoCollection =
        mongoDatabase
            .getCollection(MONGO_COLLECTION_NAME, TranslationKeyImpl.class)
            .withCodecRegistry(
                fromRegistries(
                    fromCodecs(new TranslationKeyCodec()), mongoDatabase.getCodecRegistry()));
  }
}
