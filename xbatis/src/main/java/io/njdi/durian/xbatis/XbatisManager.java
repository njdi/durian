package io.njdi.durian.xbatis;

import io.njdi.durian.xbatis.model.*;

import java.util.List;
import java.util.Map;

public interface XbatisManager {
  List<Integer> creates(Creates creates);

  int create(Create create);

  int deletes(Deletes deletes);

  int delete(Delete delete);

  List<Map<String, Object>> page(Page page);

  <T> List<T> page(Page page, Class<T> clazz);

  <T> T id(String table, String name, Object value, Class<T> clazz,
           Field... fields);

  <T> List<T> ids(String table, String name, List<Object> values,
                  Class<T> clazz, Field... fields);

  List<Map<String, Object>> ids(String table, String name,
                                List<Object> values, Field... fields);

  int updates(Updates updates);

  int update(Update update);
}
