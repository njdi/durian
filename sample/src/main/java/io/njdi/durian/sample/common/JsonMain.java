package io.njdi.durian.sample.common;

import io.njdi.durian.common.util.Json;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class JsonMain {
  public static void basicToJson() {
    String json;

    json = Json.toJson(1);
    System.out.println(json);

    json = Json.toJson(1.0);
    System.out.println(json);

    json = Json.toJson(true);
    System.out.println(json);

    json = Json.toJson("str");
    System.out.println(json);
  }

  public static void arrayToJson() {
    String json;

    json = Json.toJson(new int[]{1, 2, 3});
    System.out.println(json);

    json = Json.toJson(new double[]{1.0, 2.0, 3.0});
    System.out.println(json);

    json = Json.toJson(new boolean[]{true, false});
    System.out.println(json);

    json = Json.toJson(new String[]{"1", "2.0", "true"});
    System.out.println(json);
  }

  public static void listToJson() {
    String json;

    json = Json.toJson(List.of(1, 2, 3));
    System.out.println(json);

    json = Json.toJson(List.of(1.0, 2.0, 3.0));
    System.out.println(json);

    json = Json.toJson(List.of(true, false));
    System.out.println(json);

    json = Json.toJson(List.of("1", "2.0", "true"));
    System.out.println(json);
  }

  public static void dictToJson() {
    String json;

    json = Json.toJson(Map.of("key1", 1, "key2", 2));
    System.out.println(json);

    json = Json.toJson(Map.of("key1", 1.0, "key2", 2.0));
    System.out.println(json);

    json = Json.toJson(Map.of("key1", true, "key2", false));
    System.out.println(json);

    json = Json.toJson(Map.of("key1", "str", "key2", "2.0"));
    System.out.println(json);
  }

  public static class MyObject {
    private int a;
    private double b;
    private boolean c;
    private String d;
  }

  public static void classToJson() {
    MyObject object = new MyObject();

    object.a = 1;
    object.b = 2.0;
    object.c = true;
    object.d = "str";

    String json = Json.toJson(object);
    System.out.println(json);
  }

  public static void jsonToBasic() {
    int a = Json.fromJson("1", Json.JsonType.INT);
    System.out.println(a);
  }

  public static void jsonToArray() {
    int[] arr = Json.fromJson("[1, 2, 3]", Json.JsonType.INT_ARRAY);
    System.out.println(Arrays.toString(arr));
  }

  public static void jsonToList() {
    List<Integer> list = Json.fromJson("[1, 2, 3]", Json.JsonType.INT_LIST);
    System.out.println(list);
  }

  public static void jsonToDict() {
    Map<String, Integer> map = Json.fromJson("{'a': 1, 'b': 2}", Json.JsonType.INT_MAP);
    System.out.println(map);
  }

  public static void jsonToClass() {
    MyObject obj = Json.fromJson("{'a': 1, 'b': 2.0, 'c': true, d: 'str'}", MyObject.class);

    System.out.println(obj.a);
    System.out.println(obj.b);
    System.out.println(obj.c);
    System.out.println(obj.d);
  }

  public static void fromJson() {

  }

  public static void main(String[] args) {
    jsonToClass();
  }
}
