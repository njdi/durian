package io.njdi.durian.redis;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Main implements CommandLineRunner {
  @Override
  public void run(String... args) {

  }

  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }
}
