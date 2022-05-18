package io.njdi.durian.redis;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CacheService {
  public boolean check(String name) {
    return Objects.nonNull(name);
  }

  @Cacheable(cacheNames = {"testA"}, condition = "#result.equals(\"\")")
  public String sayHello(String name, String msg) {
    return "hello " + name + " " + msg;
  }
}
