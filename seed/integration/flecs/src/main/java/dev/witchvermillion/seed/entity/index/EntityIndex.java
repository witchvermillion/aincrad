package dev.witchvermillion.seed.entity.index;

import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import io.github.elebras1.flecs.Entity;
import java.util.Optional;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

@NullMarked
public final class EntityIndex<K> {

  private static final String KEY_CANNOT_BE_NULL = "Key cannot be null",
      ENTITY_CANNOT_BE_NULL = "Entity cannot be null";

  private final BiMap<K, Entity> entities;
  private final BiMap<Entity, K> keys;

  public EntityIndex(final BiMap<K, Entity> entities) {
    this.keys = (this.entities = requireNonNull(entities, "Entity BiMap cannot be null")).inverse();
  }

  public EntityIndex() {
    this(HashBiMap.create());
  }

  public void putEntity(final K key, final Entity entity) {
    this.entities.put(
        requireNonNull(key, KEY_CANNOT_BE_NULL), requireNonNull(entity, ENTITY_CANNOT_BE_NULL));
  }

  public @Nullable @CanIgnoreReturnValue Entity removeEntityByKey(final K key) {
    return this.entities.remove(requireNonNull(key, KEY_CANNOT_BE_NULL));
  }

  public @Nullable @CanIgnoreReturnValue K removeEntity(final Entity entity) {
    return this.keys.remove(requireNonNull(entity, ENTITY_CANNOT_BE_NULL));
  }

  public @Nullable Entity entityOrNull(final K key) {
    return this.entities.get(requireNonNull(key, KEY_CANNOT_BE_NULL));
  }

  public Optional<Entity> entity(final K key) {
    return ofNullable(this.entityOrNull(key));
  }

  public @Nullable K keyOrNull(final Entity entity) {
    return this.keys.get(requireNonNull(entity, ENTITY_CANNOT_BE_NULL));
  }

  public Optional<K> key(final Entity entity) {
    return ofNullable(this.keyOrNull(entity));
  }
}
