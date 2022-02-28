package io.njdi.durian.config;

import io.njdi.durian.config.model.ConfigBo;

import java.util.List;

public interface ConfigManager {
  String get(String name);

  String[] gets(String name);

  int getInt(String name);

  int[] getInts(String name);

  long getLong(String name);

  long[] getLongs(String name);

  float getFloat(String name);

  float[] getFloats(String name);

  double getDouble(String name);

  double[] getDoubles(String name);

  boolean getBoolean(String name);

  boolean[] getBooleans(String name);

  String get(String uid, String name);

  String[] gets(String uid, String name);

  int getInt(String uid, String name);

  int[] getInts(String uid, String name);

  long getLong(String uid, String name);

  long[] getLongs(String uid, String name);

  float getFloat(String uid, String name);

  float[] getFloats(String uid, String name);

  double getDouble(String uid, String name);

  double[] getDoubles(String uid, String name);

  boolean getBoolean(String uid, String name);

  boolean[] getBooleans(String uid, String name);

  void set(String name, String value);

  void sets(String name, String[] values);

  void setInt(String name, int value);

  void setInts(String name, int[] values);

  void setLong(String name, long value);

  void setLongs(String name, long[] values);

  void setFloat(String name, float value);

  void setFloats(String name, float[] values);

  void setDouble(String name, double value);

  void setDoubles(String name, double[] values);

  void setBoolean(String name, boolean value);

  void setBooleans(String name, boolean[] values);

  void set(String uid, String name, String value);

  void sets(String uid, String name, String[] values);

  void setInt(String uid, String name, int value);

  void setInts(String uid, String name, int[] values);

  void setLong(String uid, String name, long value);

  void setLongs(String uid, String name, long[] values);

  void setFloat(String uid, String name, float value);

  void setFloats(String uid, String name, float[] values);

  void setDouble(String uid, String name, double value);

  void setDoubles(String uid, String name, double[] values);

  void setBoolean(String uid, String name, boolean value);

  void setBooleans(String uid, String name, boolean[] values);

  List<ConfigBo> list(int page, int pageSize);

  List<ConfigBo> listByUid(String uid, int page, int pageSize);

  List<ConfigBo> listByName(String name, int page, int pageSize);

  void delete(int id);

  void delete(String uid, String name);

  void deletesByUid(String uid);

  void deletesByName(String name);
}
