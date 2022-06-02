package io.njdi.durian.common.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import io.njdi.durian.common.Constant;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class Json {
  private static final Gson GSON =
          new GsonBuilder()
                  .serializeNulls()
                  .serializeSpecialFloatingPointValues()
                  .disableHtmlEscaping()
                  .create();

  public static String toJson(Object object) throws JsonIOException {
    return GSON.toJson(object);
  }

  public static String toJson(Object object, Type type) throws JsonIOException {
    return GSON.toJson(object, type);
  }

  public static <T> T fromJson(String json, Class<T> classOfT) throws JsonSyntaxException {
    return GSON.fromJson(json, classOfT);
  }

  public static <T> T fromJson(String json, Type type) throws JsonSyntaxException {
    return GSON.fromJson(json, type);
  }

  public static JsonElement parse(String json) throws JsonParseException {
    return JsonParser.parseString(json);
  }

  public static boolean isJson(String json) {
    try {
      JsonElement element = parse(json);

      return element.isJsonObject() || element.isJsonArray();
    } catch (JsonParseException e) {
      return false;
    }
  }

  public static boolean isJsonObject(String json) {
    try {
      JsonElement element = parse(json);

      return element.isJsonObject();
    } catch (JsonParseException e) {
      return false;
    }
  }

  public static boolean isJsonArray(String json) {
    try {
      JsonElement element = parse(json);

      return element.isJsonArray();
    } catch (JsonParseException e) {
      return false;
    }
  }

  public static String bracket(String str) {
    if (Objects.isNull(str)) {
      return null;
    }

    return Constant.LEFT_BRACKET + str + Constant.RIGHT_BRACKET;
  }

  public static String brace(String str) {
    if (Objects.isNull(str)) {
      return null;
    }

    return Constant.LEFT_BRACE + str + Constant.RIGHT_BRACE;
  }

  public static class JsonType {
    // primitive
    public static final Type INT = new TypeToken<Integer>() {
    }.getType();

    public static final Type LONG = new TypeToken<Long>() {
    }.getType();

    public static final Type FLOAT = new TypeToken<Float>() {
    }.getType();

    public static final Type DOUBLE = new TypeToken<Double>() {
    }.getType();

    public static final Type BOOLEAN = new TypeToken<Boolean>() {
    }.getType();

    public static final Type STRING = new TypeToken<String>() {
    }.getType();

    // primitive array
    public static final Type INT_ARRAY = new TypeToken<int[]>() {
    }.getType();

    public static final Type LONG_ARRAY = new TypeToken<long[]>() {
    }.getType();

    public static final Type FLOAT_ARRAY = new TypeToken<float[]>() {
    }.getType();

    public static final Type DOUBLE_ARRAY = new TypeToken<double[]>() {
    }.getType();

    public static final Type BOOLEAN_ARRAY = new TypeToken<boolean[]>() {
    }.getType();

    public static final Type STRING_ARRAY = new TypeToken<String[]>() {
    }.getType();

    // primitive list
    public static final Type INT_LIST = new TypeToken<List<Integer>>() {
    }.getType();

    public static final Type LONG_LIST = new TypeToken<List<Long>>() {
    }.getType();

    public static final Type FLOAT_LIST = new TypeToken<List<Float>>() {
    }.getType();

    public static final Type DOUBLE_LIST = new TypeToken<List<Double>>() {
    }.getType();

    public static final Type BOOLEAN_LIST = new TypeToken<List<Boolean>>() {
    }.getType();

    public static final Type STRING_LIST = new TypeToken<List<String>>() {
    }.getType();

    // primitive map
    public static final Type INT_MAP = new TypeToken<Map<String, Integer>>() {
    }.getType();

    public static final Type LONG_MAP = new TypeToken<Map<String, Long>>() {
    }.getType();

    public static final Type FLOAT_MAP = new TypeToken<Map<String, Float>>() {
    }.getType();

    public static final Type DOUBLE_MAP = new TypeToken<Map<String, Double>>() {
    }.getType();

    public static final Type BOOLEAN_MAP = new TypeToken<Map<String, Boolean>>() {
    }.getType();

    public static final Type STRING_MAP = new TypeToken<Map<String, String>>() {
    }.getType();

    // object list
    public static final Type OBJECT_LIST = new TypeToken<List<Object>>() {
    }.getType();

    // object map
    public static final Type OBJECT_MAP = new TypeToken<Map<String, Object>>() {
    }.getType();
  }
}
