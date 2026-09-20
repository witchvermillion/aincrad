package dev.witchvermillion.cardinal.i18n.internal;

import static java.util.Objects.requireNonNull;

import java.time.Instant;
import java.util.Locale;
import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.jsr310.InstantCodec;

final class TranslationCodec implements Codec<TranslationImpl> {

  private static final String KEY_FIELD_NAME = "key",
      LOCALE_FIELD_NAME = "locale",
      TEMPLATE_FIELD_NAME = "template",
      CREATED_AT_FIELD_NAME = "createdAt";

  private static final Codec<Locale> LOCALE_CODEC = new LocaleCodec();
  private static final Codec<Instant> INSTANT_CODEC = new InstantCodec();

  @Override
  public TranslationImpl decode(final BsonReader bsonReader, final DecoderContext decoderContext) {
    String key = null, template = null;

    Locale locale = null;

    Instant createdAt = null;

    bsonReader.readStartDocument();

    while (bsonReader.getCurrentBsonType() != BsonType.END_OF_DOCUMENT) {
      final String fieldName = bsonReader.readName();
      switch (fieldName) {
        case KEY_FIELD_NAME -> key = bsonReader.readString();
        case LOCALE_FIELD_NAME ->
            locale = decoderContext.decodeWithChildContext(LOCALE_CODEC, bsonReader);
        case TEMPLATE_FIELD_NAME -> template = bsonReader.readString();
        case CREATED_AT_FIELD_NAME ->
            createdAt = decoderContext.decodeWithChildContext(INSTANT_CODEC, bsonReader);
      }
    }

    bsonReader.readEndDocument();

    return new TranslationImpl(
        requireNonNull(key, "Translation key cannot be null"),
        requireNonNull(locale, "Translation locale cannot be null"),
        requireNonNull(template, "Translation template cannot be null"),
        requireNonNull(createdAt, "Translation creation time cannot be null"));
  }

  @Override
  public void encode(
      final BsonWriter bsonWriter,
      final TranslationImpl translation,
      final EncoderContext encoderContext) {
    bsonWriter.writeStartDocument();

    bsonWriter.writeString(KEY_FIELD_NAME, translation.key());

    bsonWriter.writeName(LOCALE_FIELD_NAME);
    encoderContext.encodeWithChildContext(LOCALE_CODEC, bsonWriter, translation.locale());

    bsonWriter.writeName(TEMPLATE_FIELD_NAME);
    bsonWriter.writeString(translation.template());

    bsonWriter.writeName(CREATED_AT_FIELD_NAME);
    encoderContext.encodeWithChildContext(INSTANT_CODEC, bsonWriter, translation.createdAt());

    bsonWriter.writeEndDocument();
  }

  @Override
  public Class<TranslationImpl> getEncoderClass() {
    return TranslationImpl.class;
  }
}
