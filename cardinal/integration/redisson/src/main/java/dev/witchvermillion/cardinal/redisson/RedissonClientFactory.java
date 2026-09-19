package dev.witchvermillion.cardinal.redisson;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import org.jspecify.annotations.NullMarked;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

@Factory
final class RedissonClientFactory {

  @Bean(destroyMethod = "shutdown")
  @NullMarked
  RedissonClient redissonClient() {
    final Config redissonClientConfiguration = new Config();
    redissonClientConfiguration.useSingleServer().setAddress(System.getenv("REDIS_URI"));

    return Redisson.create(redissonClientConfiguration);
  }
}
