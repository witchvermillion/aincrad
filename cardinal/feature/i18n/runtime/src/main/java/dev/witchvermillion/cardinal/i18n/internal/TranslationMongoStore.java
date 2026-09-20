package dev.witchvermillion.cardinal.i18n.internal;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Indexes.ascending;
import static org.bson.codecs.configuration.CodecRegistries.fromCodecs;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import static reactor.core.publisher.Mono.from;

import com.mongodb.client.model.IndexOptions;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;
import io.avaje.inject.PostConstruct;
import jakarta.inject.Singleton;
import java.util.Locale;
import reactor.core.publisher.Mono;

@Singleton
final class TranslationMongoStore {

  private static final String MONGO_COLLECTION_NAME = "translations",
      KEY_FIELD_NAME = "key",
      LOCALE_FIELD_NAME = "locale";

  private final MongoCollection<TranslationImpl> translationMongoCollection;

  TranslationMongoStore(final MongoDatabase mongoDatabase) {
    this.translationMongoCollection =
        mongoDatabase
            .getCollection(MONGO_COLLECTION_NAME, TranslationImpl.class)
            .withCodecRegistry(
                fromRegistries(
                    fromCodecs(new TranslationCodec()), mongoDatabase.getCodecRegistry()));
  }

  @PostConstruct
  void createIndex() {
    from(this.translationMongoCollection.createIndex(
            ascending(KEY_FIELD_NAME, LOCALE_FIELD_NAME), new IndexOptions().unique(true)))
        .block();
  }

  Mono<TranslationImpl> findTranslation(final String translationKey, final Locale locale) {
    return from(
        this.translationMongoCollection
            .find(and(eq(KEY_FIELD_NAME, translationKey), eq(LOCALE_FIELD_NAME, locale)))
            .first());
  }
}
