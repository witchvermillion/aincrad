package dev.witchvermillion.cardinal.i18n.internal;

import static java.util.Objects.requireNonNull;

import java.time.Instant;
import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.jsr310.InstantCodec;

final class TranslationKeyCodec implements Codec<TranslationKeyImpl> {

  private static final String NAMESPACE_FIELD_NAME = "namespace",
      NAME_FIELD_NAME = "name",
      CREATED_AT_FIELD_NAME = "createdAt";

  private static final Codec<Instant> INSTANT_CODEC = new InstantCodec();

  @Override
  public TranslationKeyImpl decode(
      final BsonReader bsonReader, final DecoderContext decoderContext) {
    String namespace = null, name = null;

    Instant createdAt = null;

    bsonReader.readStartDocument();

    while (bsonReader.getCurrentBsonType() != BsonType.END_OF_DOCUMENT) {
      final String fieldName = bsonReader.readName();
      switch (fieldName) {
        case NAMESPACE_FIELD_NAME -> namespace = bsonReader.readString();
        case NAME_FIELD_NAME -> name = bsonReader.readString();
        case CREATED_AT_FIELD_NAME ->
            createdAt = decoderContext.decodeWithChildContext(INSTANT_CODEC, bsonReader);

        default -> bsonReader.skipValue();
      }
    }

    bsonReader.readEndDocument();

    return new TranslationKeyImpl(
        requireNonNull(namespace, "Translation key namespace cannot be null"),
        requireNonNull(name, "Translation key name cannot be null"),
        requireNonNull(createdAt, "Translation key creation timestamp cannot be null"));
  }

  @Override
  public void encode(
      final BsonWriter bsonWriter,
      final TranslationKeyImpl translationKey,
      final EncoderContext encoderContext) {
    bsonWriter.writeStartDocument();

    bsonWriter.writeString(NAMESPACE_FIELD_NAME, translationKey.namespace());
    bsonWriter.writeString(NAME_FIELD_NAME, translationKey.name());

    encoderContext.encodeWithChildContext(INSTANT_CODEC, bsonWriter, translationKey.createdAt());

    bsonWriter.writeEndDocument();
  }

  @Override
  public Class<TranslationKeyImpl> getEncoderClass() {
    return TranslationKeyImpl.class;
  }
}
