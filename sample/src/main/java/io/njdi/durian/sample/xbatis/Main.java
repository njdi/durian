package io.njdi.durian.sample.xbatis;

import io.njdi.durian.xbatis.XbatisManager;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"io.njdi.durian.sample.xbatis", "io.njdi.durian.xbatis"})
@MapperScan(basePackages = {"io.njdi.durian.xbatis.core"})
public class Main implements CommandLineRunner {
  @Autowired
  private XbatisManager xbatisManager;

  @Override
  public void run(String... args) {

  }

  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }
}
