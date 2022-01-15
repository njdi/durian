package io.njdi.durian.xbatis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

public class LombokTester {
  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @SuperBuilder
  public abstract static class Parent {
    private String a;
  }


  @Data
  @EqualsAndHashCode(callSuper = true)
  @ToString(callSuper = true)
  @SuperBuilder
  @AllArgsConstructor
  public static class Child extends Parent {

  }

  public static void main(String[] args) {
    Child child = Child.builder().a("hello").build();
    System.out.println(child);
  }
}
