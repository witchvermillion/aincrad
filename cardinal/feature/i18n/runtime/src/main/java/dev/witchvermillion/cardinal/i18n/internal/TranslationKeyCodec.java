package dev.witchvermillion.cardinal.i18n.internal;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

final class TranslationKeyCodec implements Codec<TranslationKeyImpl> {

  @Override
  public TranslationKeyImpl decode(
      final BsonReader bsonReader, final DecoderContext decoderContext) {
    return null;
  }

  @Override
  public void encode(
      final BsonWriter bsonWriter,
      final TranslationKeyImpl translationKey,
      final EncoderContext encoderContext) {}

  @Override
  public Class<TranslationKeyImpl> getEncoderClass() {
    return TranslationKeyImpl.class;
  }
}
