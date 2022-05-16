package io.njdi.durian.redis;

import io.njdi.durian.common.Constant;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.cache.RedisCacheConfiguration;

import java.time.Duration;

@Configuration(proxyBeanMethods = false)
public class RedisConfiguration {
  private static final Logger LOGGER = LoggerFactory.getLogger(RedisConfiguration.class);

  @Autowired
  private Environment env;

  @Bean
  public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
    String[] names = env.getProperty("spring.cache.cache-names", String[].class, new String[0]);

    return (builder) -> {
      if (ArrayUtils.isEmpty(names)) {
        return;
      }

      for (String name : names) {
        int millis = env.getProperty("spring.cache." + name + ".time-to-live", Integer.class, Constant.ZERO);
        LOGGER.info("redis cache: {}, ttl: {} ms", name, millis);

        Duration ttl = Duration.ofMillis(millis);

        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
        configuration = configuration.entryTtl(ttl);

        builder.withCacheConfiguration(name, configuration);
      }
    };
  }
}
