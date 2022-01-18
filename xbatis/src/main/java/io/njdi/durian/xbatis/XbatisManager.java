package io.njdi.durian.xbatis;

import io.njdi.durian.xbatis.model.Create;
import io.njdi.durian.xbatis.model.Creates;
import io.njdi.durian.xbatis.model.Delete;
import io.njdi.durian.xbatis.model.Deletes;
import io.njdi.durian.xbatis.model.Field;
import io.njdi.durian.xbatis.model.Page;
import io.njdi.durian.xbatis.model.Update;
import io.njdi.durian.xbatis.model.Updates;

import java.util.List;
import java.util.Map;

public interface XbatisManager {
  List<Integer> creates(Creates creates);

  int create(Create create);

  int deletes(Deletes deletes);

  int delete(Delete delete);

  List<Map<String, Object>> page(Page page);

  <T> List<T> page(Page page, Class<T> clazz);

  Map<String, Object> id(String table, String name, Object value, Field... fields);

  Map<String, Object> id(String table, Integer id, Field... fields);

  <T> T id(String table, String name, Object value, Class<T> clazz, Field... fields);

  <T> T id(String table, Integer id, Class<T> clazz, Field... fields);

  List<Map<String, Object>> ids(String table, String name, List<Object> values, Field... fields);

  List<Map<String, Object>> ids(String table, List<Integer> ids, Field... fields);

  <T> List<T> ids(String table, String name, List<Object> values, Class<T> clazz, Field... fields);

  <T> List<T> ids(String table, List<Integer> ids, Class<T> clazz, Field... fields);

  int updates(Updates updates);

  int update(Update update);
}
