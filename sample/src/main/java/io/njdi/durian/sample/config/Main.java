package io.njdi.durian.sample.config;

import io.njdi.durian.config.ConfigManager;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

@SpringBootApplication
@ComponentScan({"io.njdi.durian.sample.config", "io.njdi.durian.xbatis", "io.njdi.durian.config"})
@MapperScan(basePackages = {"io.njdi.durian.xbatis.core"})
@EnableCaching
@Slf4j
public class Main implements CommandLineRunner {
  @Autowired
  private ConfigManager configManager;

  public void simple() {
    configManager.set("string", "value");
    configManager.setInt("int", 1);
    configManager.setLong("long", 1L);
    configManager.setFloat("float", 1.0F);
    configManager.setDouble("double", 1.0);
    configManager.setBoolean("boolean", true);

    configManager.sets("strings", new String[]{"value"});
    configManager.setInts("ints", new int[]{1});
    configManager.setLongs("longs", new long[]{1L});
    configManager.setFloats("floats", new float[]{1.0F});
    configManager.setDoubles("doubles", new double[]{1.0});
    configManager.setBooleans("booleans", new boolean[]{true});

    System.out.println(configManager.get("string"));
    System.out.println(configManager.getInt("int"));
    System.out.println(configManager.getLong("long"));
    System.out.println(configManager.getFloat("float"));
    System.out.println(configManager.getDouble("double"));
    System.out.println(configManager.getBoolean("boolean"));

    System.out.println(Arrays.toString(configManager.gets("strings")));
    System.out.println(Arrays.toString(configManager.getInts("ints")));
    System.out.println(Arrays.toString(configManager.getLongs("longs")));
    System.out.println(Arrays.toString(configManager.getFloats("floats")));
    System.out.println(Arrays.toString(configManager.getDoubles("doubles")));
    System.out.println(Arrays.toString(configManager.getBooleans("booleans")));
  }

  public void user() {
    configManager.set("string", "value");
    configManager.set("user", "string", "value");

    System.out.println(configManager.get("string"));
    System.out.println(configManager.get("user", "string"));
    System.out.println(configManager.get("user2", "string"));

    System.out.println(configManager.get("string2"));
    System.out.println(configManager.get("user", "string2"));
    System.out.println(configManager.get("user2", "string2"));
  }

  public void list() {
    System.out.println(configManager.list(1, 5));
    System.out.println(configManager.listByUid("user", 1, 5));
    System.out.println(configManager.listByName("string", 1, 5));
  }

  public void delete() {
    configManager.delete(1);
    configManager.delete("string", "value");
    configManager.deletesByUid("system");
    configManager.deletesByName("string");
  }

  public void cache() {
    for (int index = 0; index < 10; index++) {
      System.out.println(configManager.get("string"));
      try {
        Thread.sleep(10000);
      } catch (InterruptedException e) {
        // do nothing
      }
    }
  }

  @Override
  public void run(String... args) {
    cache();
  }

  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }
}
