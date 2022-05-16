package io.njdi.durian.redis;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class CacheService {
  @Cacheable(cacheNames = {"testA"})
  public String sayHello(String name) {
    return "say hello " + name;
  }
}
