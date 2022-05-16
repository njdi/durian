package io.njdi.durian.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTest {
  @Autowired
  private CacheService cacheService;

  @Test
  public void cache() {
    String result = cacheService.sayHello("world");
    System.out.println(result);
  }
}
