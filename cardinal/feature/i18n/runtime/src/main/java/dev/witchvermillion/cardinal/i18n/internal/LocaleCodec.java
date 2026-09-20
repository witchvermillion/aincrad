package dev.witchvermillion.cardinal.i18n.internal;

import java.util.Locale;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

final class LocaleCodec implements Codec<Locale> {

  @Override
  public Locale decode(final BsonReader bsonReader, final DecoderContext decoderContext) {
    return new Locale.Builder().setLanguage(bsonReader.readString()).build();
  }

  @Override
  public void encode(
      final BsonWriter bsonWriter, final Locale locale, final EncoderContext encoderContext) {
    bsonWriter.writeString(locale.getLanguage());
  }

  @Override
  public Class<Locale> getEncoderClass() {
    return Locale.class;
  }
}
