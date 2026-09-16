package dev.witchvermillion.cardinal.redisson.reactive.client.factory;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import org.jspecify.annotations.NullMarked;
import org.redisson.api.RedissonClient;
import org.redisson.api.RedissonReactiveClient;

@Factory
final class RedissonReactiveClientFactory {

  @Bean
  @NullMarked
  RedissonReactiveClient redissonReactiveClient(final RedissonClient redissonClient) {
    return redissonClient.reactive();
  }
}
