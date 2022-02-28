package io.njdi.durian.config.impl;

import io.njdi.durian.common.util.Json;
import io.njdi.durian.config.ConfigManager;
import io.njdi.durian.config.model.ConfigBo;
import io.njdi.durian.config.model.ConfigTable;
import io.njdi.durian.xbatis.XbatisManager;
import io.njdi.durian.xbatis.model.Create;
import io.njdi.durian.xbatis.model.Delete;
import io.njdi.durian.xbatis.model.Page;
import io.njdi.durian.xbatis.model.Pair;
import io.njdi.durian.xbatis.model.where.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@CacheConfig(cacheNames = {"durian.config"})
public class ConfigManagerImpl implements ConfigManager {
  @Value("${durian.config.table:config}")
  private String table;

  @Value("${durian.config.system:system}")
  private String system;

  @Autowired
  private XbatisManager xbatisManager;

  @Override
  @Cacheable
  public String get(String name) {
    return get(system, name);
  }

  @Override
  @Cacheable
  public String[] gets(String name) {
    return gets(system, name);
  }

  @Override
  @Cacheable
  public int getInt(String name) {
    return getInt(system, name);
  }

  @Override
  @Cacheable
  public int[] getInts(String name) {
    return getInts(system, name);
  }

  @Override
  @Cacheable
  public long getLong(String name) {
    return getLong(system, name);
  }

  @Override
  @Cacheable
  public long[] getLongs(String name) {
    return getLongs(system, name);
  }

  @Override
  @Cacheable
  public float getFloat(String name) {
    return getFloat(system, name);
  }

  @Override
  @Cacheable
  public float[] getFloats(String name) {
    return getFloats(system, name);
  }

  @Override
  @Cacheable
  public double getDouble(String name) {
    return getDouble(system, name);
  }

  @Override
  @Cacheable
  public double[] getDoubles(String name) {
    return getDoubles(system, name);
  }

  @Override
  @Cacheable
  public boolean getBoolean(String name) {
    return getBoolean(system, name);
  }

  @Override
  @Cacheable
  public boolean[] getBooleans(String name) {
    return getBooleans(system, name);
  }

  @Override
  @Cacheable
  public String get(String uid, String name) {
    Page page = Page.builder()
            .table(table)
            .where(new Filter(ConfigTable.COL_UID, Filter.Operator.IN, Arrays.asList(uid, system)))
            .where(new Filter(ConfigTable.COL_NAME, Filter.Operator.EQ, Collections.singletonList(name)))
            .build();

    List<ConfigBo> configBos = xbatisManager.page(page, ConfigBo.class);
    if (CollectionUtils.isEmpty(configBos)) {
      return null;
    }

    configBos.sort((c1, c2) -> {
      if (system.equals(c1.getName())) {
        return 1;
      }

      if (system.equals(c2.getName())) {
        return -1;
      }

      return 0;
    });

    return configBos.get(0).getValue();
  }

  @Override
  @Cacheable
  public String[] gets(String uid, String name) {
    String json = get(uid, name);
    if (!StringUtils.hasLength(json)) {
      return null;
    }

    return Json.fromJson(json, Json.JsonType.STRING_ARRAY);
  }

  @Override
  @Cacheable
  public int getInt(String uid, String name) {
    String value = get(uid, name);
    if (!StringUtils.hasLength(value)) {
      return -1;
    }

    return Integer.parseInt(value);
  }

  @Override
  @Cacheable
  public int[] getInts(String uid, String name) {
    String json = get(uid, name);
    if (!StringUtils.hasLength(json)) {
      return null;
    }

    return Json.fromJson(json, Json.JsonType.INT_ARRAY);
  }

  @Override
  @Cacheable
  public long getLong(String uid, String name) {
    String value = get(uid, name);
    if (!StringUtils.hasLength(value)) {
      return -1;
    }

    return Long.parseLong(value);
  }

  @Override
  @Cacheable
  public long[] getLongs(String uid, String name) {
    String json = get(uid, name);
    if (!StringUtils.hasLength(json)) {
      return null;
    }

    return Json.fromJson(json, Json.JsonType.LONG_ARRAY);
  }

  @Override
  @Cacheable
  public float getFloat(String uid, String name) {
    String value = get(uid, name);
    if (!StringUtils.hasLength(value)) {
      return -1.0F;
    }

    return Float.parseFloat(value);
  }

  @Override
  @Cacheable
  public float[] getFloats(String uid, String name) {
    String json = get(uid, name);
    if (!StringUtils.hasLength(json)) {
      return null;
    }

    return Json.fromJson(json, Json.JsonType.FLOAT_ARRAY);
  }

  @Override
  @Cacheable
  public double getDouble(String uid, String name) {
    String value = get(uid, name);
    if (!StringUtils.hasLength(value)) {
      return -1.0;
    }

    return Double.parseDouble(value);
  }

  @Override
  @Cacheable
  public double[] getDoubles(String uid, String name) {
    String json = get(uid, name);
    if (!StringUtils.hasLength(json)) {
      return null;
    }

    return Json.fromJson(json, Json.JsonType.DOUBLE_ARRAY);
  }

  @Override
  @Cacheable
  public boolean getBoolean(String uid, String name) {
    String value = get(uid, name);
    if (!StringUtils.hasLength(value)) {
      return false;
    }

    return Boolean.parseBoolean(value);
  }

  @Override
  @Cacheable
  public boolean[] getBooleans(String uid, String name) {
    String json = get(uid, name);
    if (!StringUtils.hasLength(json)) {
      return null;
    }

    return Json.fromJson(json, Json.JsonType.BOOLEAN_ARRAY);
  }

  @Override
  public void set(String name, String value) {
    set(system, name, value);
  }

  @Override
  public void sets(String name, String[] values) {
    set(name, Json.toJson(values));
  }

  @Override
  public void setInt(String name, int value) {
    set(name, String.valueOf(value));
  }

  @Override
  public void setInts(String name, int[] values) {
    set(name, Json.toJson(values));
  }

  @Override
  public void setLong(String name, long value) {
    set(name, String.valueOf(value));
  }

  @Override
  public void setLongs(String name, long[] values) {
    set(name, Json.toJson(values));
  }

  @Override
  public void setFloat(String name, float value) {
    set(name, String.valueOf(value));
  }

  @Override
  public void setFloats(String name, float[] values) {
    set(name, Json.toJson(values));
  }

  @Override
  public void setDouble(String name, double value) {
    set(name, String.valueOf(value));
  }

  @Override
  public void setDoubles(String name, double[] values) {
    set(name, Json.toJson(values));
  }

  @Override
  public void setBoolean(String name, boolean value) {
    setBoolean(system, name, value);
  }

  @Override
  public void setBooleans(String name, boolean[] values) {
    setBooleans(system, name, values);
  }

  @Override
  public void set(String uid, String name, String value) {
    Create create = Create.builder()
            .table(table)
            .pair(new Pair<>(ConfigTable.COL_UID, uid))
            .pair(new Pair<>(ConfigTable.COL_NAME, name))
            .pair(new Pair<>(ConfigTable.COL_VALUE, value))
            .update(true)
            .build();

    xbatisManager.create(create);
  }

  @Override
  public void sets(String uid, String name, String[] values) {
    set(uid, name, Json.toJson(values));
  }

  @Override
  public void setInt(String uid, String name, int value) {
    set(uid, name, String.valueOf(value));
  }

  @Override
  public void setInts(String uid, String name, int[] values) {
    set(uid, name, Json.toJson(values));
  }

  @Override
  public void setLong(String uid, String name, long value) {
    set(uid, name, String.valueOf(value));
  }

  @Override
  public void setLongs(String uid, String name, long[] values) {
    set(uid, name, Json.toJson(values));
  }

  @Override
  public void setFloat(String uid, String name, float value) {
    set(uid, name, String.valueOf(value));
  }

  @Override
  public void setFloats(String uid, String name, float[] values) {
    set(uid, name, Json.toJson(values));
  }

  @Override
  public void setDouble(String uid, String name, double value) {
    set(uid, name, String.valueOf(value));
  }

  @Override
  public void setDoubles(String uid, String name, double[] values) {
    set(uid, name, Json.toJson(values));
  }

  @Override
  public void setBoolean(String uid, String name, boolean value) {
    set(uid, name, String.valueOf(value));
  }

  @Override
  public void setBooleans(String uid, String name, boolean[] values) {
    set(uid, name, Json.toJson(values));
  }

  @Override
  public List<ConfigBo> list(int page, int pageSize) {
    Page select = Page.builder()
            .table(table)
            .limit(pageSize)
            .offset((page - 1) * pageSize)
            .build();

    return xbatisManager.page(select, ConfigBo.class);
  }

  @Override
  public List<ConfigBo> listByUid(String uid, int page, int pageSize) {
    Page select = Page.builder()
            .table(table)
            .where(new Filter(ConfigTable.COL_UID, Filter.Operator.EQ, List.of(uid)))
            .limit(pageSize)
            .offset((page - 1) * pageSize)
            .build();

    return xbatisManager.page(select, ConfigBo.class);
  }

  @Override
  public List<ConfigBo> listByName(String name, int page, int pageSize) {
    Page select = Page.builder()
            .table(table)
            .where(new Filter(ConfigTable.COL_NAME, Filter.Operator.EQ, List.of(name)))
            .limit(pageSize)
            .offset((page - 1) * pageSize)
            .build();

    return xbatisManager.page(select, ConfigBo.class);
  }

  @Override
  public void delete(int id) {
    Delete delete = Delete.builder()
            .table(table)
            .where(new Filter(ConfigTable.COL_ID, Filter.Operator.EQ, List.of(id)))
            .build();

    xbatisManager.delete(delete);
  }

  @Override
  public void delete(String uid, String name) {
    Delete delete = Delete.builder()
            .table(table)
            .where(new Filter(ConfigTable.COL_UID, Filter.Operator.EQ, List.of(uid)))
            .where(new Filter(ConfigTable.COL_NAME, Filter.Operator.EQ, List.of(name)))
            .build();

    xbatisManager.delete(delete);
  }

  @Override
  public void deletesByUid(String uid) {
    Delete delete = Delete.builder()
            .table(table)
            .where(new Filter(ConfigTable.COL_UID, Filter.Operator.EQ, List.of(uid)))
            .build();

    xbatisManager.delete(delete);
  }

  @Override
  public void deletesByName(String name) {
    Delete delete = Delete.builder()
            .table(table)
            .where(new Filter(ConfigTable.COL_NAME, Filter.Operator.EQ, List.of(name)))
            .build();

    xbatisManager.delete(delete);
  }
}
