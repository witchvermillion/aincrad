package dev.witchvermillion.cardinal.profile.internal;

import static java.util.Objects.requireNonNull;

import java.time.Instant;
import java.util.UUID;
import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.BsonWriter;
import org.bson.UuidRepresentation;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.UuidCodec;
import org.bson.codecs.jsr310.InstantCodec;

final class ProfileCodec implements Codec<ProfileImpl> {

  static final String CREATED_AT_FIELD_NAME = "createdAt";

  private static final String ID_FIELD_NAME = "_id";

  private static final Codec<UUID> UUID_CODEC = new UuidCodec(UuidRepresentation.STANDARD);
  private static final Codec<Instant> INSTANT_CODEC = new InstantCodec();

  @Override
  public ProfileImpl decode(final BsonReader bsonReader, final DecoderContext decoderContext) {
    UUID id = null;
    Instant createdAt = null;

    bsonReader.readStartDocument();

    while (bsonReader.readBsonType() != BsonType.END_OF_DOCUMENT) {
      final String fieldName = bsonReader.readName();
      switch (fieldName) {
        case ID_FIELD_NAME -> id = decoderContext.decodeWithChildContext(UUID_CODEC, bsonReader);

        case CREATED_AT_FIELD_NAME ->
            createdAt = decoderContext.decodeWithChildContext(INSTANT_CODEC, bsonReader);

        default -> bsonReader.skipValue();
      }
    }

    bsonReader.readEndDocument();

    return new ProfileImpl(
        requireNonNull(id, "Profile ID cannot be null"),
        requireNonNull(createdAt, "Profile creation timestamp cannot be null"));
  }

  @Override
  public void encode(
      final BsonWriter bsonWriter, final ProfileImpl profile, final EncoderContext encoderContext) {
    bsonWriter.writeStartDocument();

    bsonWriter.writeName(ID_FIELD_NAME);
    encoderContext.encodeWithChildContext(UUID_CODEC, bsonWriter, profile.id());

    bsonWriter.writeName(CREATED_AT_FIELD_NAME);
    encoderContext.encodeWithChildContext(INSTANT_CODEC, bsonWriter, profile.createdAt());

    bsonWriter.writeEndDocument();
  }

  @Override
  public Class<ProfileImpl> getEncoderClass() {
    return ProfileImpl.class;
  }
}
