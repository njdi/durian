package io.njdi.durian.xbatis.core;

import io.njdi.durian.xbatis.model.Create;
import io.njdi.durian.xbatis.model.Delete;
import io.njdi.durian.xbatis.model.Field;
import io.njdi.durian.xbatis.model.Order;
import io.njdi.durian.xbatis.model.Page;
import io.njdi.durian.xbatis.model.Pair;
import io.njdi.durian.xbatis.model.Update;
import io.njdi.durian.xbatis.model.schema.Database;
import io.njdi.durian.xbatis.model.where.Filter;
import io.njdi.durian.xbatis.model.where.OrFilter;
import io.njdi.durian.xbatis.model.where.Where;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Slf4j
public class Transformer {
  @Autowired
  private Database database;

  private List<String> acquireImplicitFields(String table) {
    return database.containTable(table) ?
            database.getTable(table).getImplicitColumnNames() :
            Collections.emptyList();
  }

  private Map<String, String> acquireFieldAliases(String table) {
    return database.containTable(table) ?
            database.getTable(table).getColumnNameToAliases() :
            Collections.emptyMap();
  }

  private Map<String, String> acquireTableAliases() {
    return database.getTableAliases();
  }

  private List<Field> transformFields(String table, List<Field> fields) {
    if (StringUtils.isEmpty(table) || !database.containTable(table)) {
      return fields;
    }

    if (CollectionUtils.isEmpty(fields)) {
      List<String> implicitFields = acquireImplicitFields(table);

      fields = implicitFields.stream()
              .map(field -> Field.builder().name(field).build())
              .collect(Collectors.toList());
    }

    Map<String, String> aliases = acquireFieldAliases(table);

    fields = fields.stream()
            .peek(field -> {
              if (field.isExpr()) {
                return;
              }

              String name = field.getName();
              if (!aliases.containsKey(name)) {
                return;
              }

              field.setName(aliases.get(name));
              field.setAlias(name);
            })
            .collect(Collectors.toList());

    return fields;
  }

  private String transformTable(String table) {
    if (StringUtils.isEmpty(table) || !database.containTable(table)) {
      return table;
    }

    Map<String, String> tableAlias = acquireTableAliases();

    return tableAlias.getOrDefault(table, table);
  }

  private OrFilter transformOrFilter(String table, OrFilter orFilter) {
    List<Filter> filters = orFilter.getFilters();
    filters = filters.stream().map(filter -> transformFilter(table, filter)).toList();

    orFilter.setFilters(filters);

    return orFilter;
  }

  private Filter transformFilter(String table, Filter filter) {
    if (filter.isExpr()) {
      return filter;
    }

    Map<String, String> aliases = acquireFieldAliases(table);

    String name = filter.getName();
    filter.setName(aliases.getOrDefault(name, name));

    return filter;
  }

  private Where transformWhere(String table, Where where) {
    if (where instanceof OrFilter) {
      return transformOrFilter(table, (OrFilter) where);
    } else {
      return transformFilter(table, (Filter) where);
    }
  }

  private List<Where> transformWheres(String table,
                                      List<Where> wheres) {
    if (StringUtils.isEmpty(table)
            || !database.containTable(table)
            || CollectionUtils.isEmpty(wheres)) {
      return wheres;
    }

    wheres = wheres.stream()
            .map(where -> transformWhere(table, where))
            .collect(Collectors.toList());

    return wheres;
  }

  private List<String> transformGroups(String table, List<String> groups) {
    if (StringUtils.isEmpty(table)
            || !database.containTable(table)
            || CollectionUtils.isEmpty(groups)) {
      return groups;
    }

    Map<String, String> aliases = acquireFieldAliases(table);

    groups = groups.stream()
            .map(group -> aliases.getOrDefault(group, group))
            .collect(Collectors.toList());

    return groups;
  }

  private List<Order> transformOrders(String table, List<Order> orders) {
    if (StringUtils.isEmpty(table)
            || !database.containTable(table)
            || CollectionUtils.isEmpty(orders)) {
      return orders;
    }

    Map<String, String> aliases = acquireFieldAliases(table);

    orders = orders.stream()
            .peek(order -> {
              if (order.isExpr()) {
                return;
              }

              String name = order.getName();
              order.setName(aliases.getOrDefault(name, name));
            })
            .collect(Collectors.toList());

    return orders;
  }

  private List<Pair<?>> transformPairs(String table, List<Pair<?>> pairs) {
    if (CollectionUtils.isEmpty(pairs)) {
      return pairs;
    }

    Map<String, String> aliases = acquireFieldAliases(table);

    pairs = pairs.stream()
            .peek(pair -> {
              String name = pair.getName();
              pair.setName(aliases.getOrDefault(name, name));
            })
            .collect(Collectors.toList());

    return pairs;
  }

  public Create transform(Create create) {
    if (Objects.isNull(create)) {
      return null;
    }

    create.setPairs(transformPairs(create.getTable(), create.getPairs()));
    create.setTable(transformTable(create.getTable()));

    return create;
  }

  public Delete transform(Delete delete) {
    if (Objects.isNull(delete)) {
      return null;
    }

    delete.setWheres(transformWheres(delete.getTable(), delete.getWheres()));
    delete.setTable(transformTable(delete.getTable()));

    return delete;
  }

  public Page transform(Page page) {
    if (Objects.isNull(page)) {
      return null;
    }

    page.setFields(transformFields(page.getTable(), page.getFields()));
    page.setWheres(transformWheres(page.getTable(), page.getWheres()));
    page.setGroups(transformGroups(page.getTable(), page.getGroups()));
    page.setHavings(transformWheres(page.getTable(), page.getHavings()));
    page.setOrders(transformOrders(page.getTable(), page.getOrders()));
    page.setTable(transformTable(page.getTable()));

    return page;
  }

  public Update transform(Update update) {
    if (Objects.isNull(update)) {
      return null;
    }

    update.setPairs(transformPairs(update.getTable(),
            update.getPairs()));
    update.setWheres(transformWheres(update.getTable(),
            update.getWheres()));
    update.setTable(transformTable(update.getTable()));

    return update;
  }
}
