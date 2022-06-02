package io.njoi.durian.common.util;

import io.njdi.durian.common.util.Json;
import org.junit.Test;

public class JsonTest {
  @Test
  public void isJson() {
    System.out.println(Json.isJson("{}"));
    System.out.println(Json.isJson("[]"));
    System.out.println(Json.isJson("a"));
  }

  @Test
  public void isJsonObject() {
    System.out.println(Json.isJsonObject("{}"));
    System.out.println(Json.isJsonObject("[]"));
  }

  @Test
  public void isJsonArray() {
    System.out.println(Json.isJsonArray("{}"));
    System.out.println(Json.isJsonArray("[]"));
  }

  @Test
  public void bracket() {
    System.out.println(Json.bracket(null));
    System.out.println(Json.bracket("abc"));
  }

  @Test
  public void brace() {
    System.out.println(Json.brace(null));
    System.out.println(Json.brace("123"));
  }
}
