package dev.witchvermillion.cardinal.i18n.internal;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

final class TranslationCodec implements Codec<TranslationImpl> {

  private static final Codec<TranslationKeyImpl> TRANSLATION_KEY_CODEC = new TranslationKeyCodec();

  @Override
  public TranslationImpl decode(final BsonReader bsonReader, final DecoderContext decoderContext) {
    return null;
  }

  @Override
  public void encode(
      final BsonWriter bsonWriter,
      final TranslationImpl translation,
      final EncoderContext encoderContext) {}

  @Override
  public Class<TranslationImpl> getEncoderClass() {
    return TranslationImpl.class;
  }
}
