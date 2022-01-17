package io.njdi.durian.xbatis.core;

import io.njdi.durian.common.util.Bean;
import io.njdi.durian.xbatis.XbatisManager;
import io.njdi.durian.xbatis.model.Create;
import io.njdi.durian.xbatis.model.Creates;
import io.njdi.durian.xbatis.model.Delete;
import io.njdi.durian.xbatis.model.Deletes;
import io.njdi.durian.xbatis.model.Field;
import io.njdi.durian.xbatis.model.where.Filter;
import io.njdi.durian.xbatis.model.Page;
import io.njdi.durian.xbatis.model.Update;
import io.njdi.durian.xbatis.model.Updates;
import io.njdi.durian.xbatis.model.schema.Database;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Slf4j
public class XbatisManagerImpl implements XbatisManager {
  private final Database database;

  @Autowired
  public XbatisManagerImpl(Database database) {
    this.database = database;
    this.database.check();
  }

  @Autowired
  private Transformer transformer;
  @Autowired
  private Validator validator;

  @Autowired
  private XbatisMapper xbatisMapper;

  @Override
  public List<Integer> creates(Creates creates) {
    log.debug("creates: {}", creates);

    List<Integer> ids = new ArrayList<>();

    for (Create create : creates.getCreates()) {
      ids.add(create(create));
    }

    return ids;
  }

  @Override
  public int create(Create create) {
    log.debug("create: {}", create);

    validator.validate(create);
    create = transformer.transform(create);

    log.debug("create(transformed): {}", create);

    xbatisMapper.create(create);

    return create.getId();
  }

  @Override
  public int deletes(Deletes deletes) {
    log.debug("deletes: {}", deletes);

    int affectedRows = 0;

    for (Delete delete : deletes.getDeletes()) {
      affectedRows += delete(delete);
    }

    return affectedRows;
  }

  @Override
  public int delete(Delete delete) {
    log.debug("delete: {}", delete);

    validator.validate(delete);
    delete = transformer.transform(delete);

    log.debug("delete(transformed): {}", delete);

    return xbatisMapper.delete(delete);
  }

  @Override
  public List<Map<String, Object>> page(Page page) {
    log.debug("page: {}", page);

    validator.validate(page);
    page = transformer.transform(page);

    log.debug("page(transformed): {}", page);

    return xbatisMapper.page(page);
  }

  @Override
  public <T> List<T> page(Page page, Class<T> clazz) {
    log.debug("page: {}, clazz: {}", page, clazz);

    return Bean.converts(clazz, page(page));
  }

  @Override
  public <T> T id(String table, String name, Object value, Class<T> clazz,
                  Field... fields) {
    log.debug("id table: {}, name: {}, value: {}, clazz: {}, fields: {}", table, name, value, clazz, fields);

    List<T> objs = ids(table, name, List.of(value), clazz, fields);

    return CollectionUtils.isNotEmpty(objs) ? objs.get(0) : null;
  }

  @Override
  public <T> List<T> ids(String table, String name, List<Object> values,
                         Class<T> clazz, Field... fields) {
    log.debug("ids table: {}, name: {}, value: {}, clazz: {}, fields: {}", table, name, values, clazz, fields);

    if (StringUtils.isEmpty(table)
            || StringUtils.isEmpty(name)
            || CollectionUtils.isEmpty(values)
            || Objects.isNull(clazz)) {
      return Collections.emptyList();
    }


    List<Map<String, Object>> rows = ids(table, name, values, fields);

    return Bean.converts(clazz, rows);
  }

  @Override
  public List<Map<String, Object>> ids(String table, String name,
                                       List<Object> values, Field... fields) {
    log.debug("ids table: {}, name: {}, value: {}, fields: {}", table, name, values, fields);

    if (StringUtils.isEmpty(table)
            || StringUtils.isEmpty(name)
            || CollectionUtils.isEmpty(values)) {
      return Collections.emptyList();
    }

    Filter filterWithIds = Filter.builder()
            .name(name)
            .operator(Filter.Operator.IN)
            .values(values)
            .build();

    Page page = Page.builder()
            .fields(Arrays.asList(fields))
            .table(table)
            .where(filterWithIds)
            .build();

    List<Map<String, Object>> rows = page(page);

    Map<Object, Map<String, Object>> idToRows =
            rows.stream()
                    .collect(Collectors.toMap(row -> row.get(name),
                            Function.identity()));
    rows = values.stream()
            .map(id -> idToRows.getOrDefault(id, null))
            .toList();

    return rows;
  }

  @Override
  public int updates(Updates updates) {
    log.debug("updates: {}", updates);

    int affectedRows = 0;

    for (Update update : updates.getUpdates()) {
      affectedRows += update(update);
    }

    return affectedRows;
  }

  @Override
  public int update(Update update) {
    log.debug("update: {}", update);

    validator.validate(update);
    update = transformer.transform(update);

    log.debug("delete(transformed): {}", update);

    return xbatisMapper.update(update);
  }
}
