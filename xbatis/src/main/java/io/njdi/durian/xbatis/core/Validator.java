package io.njdi.durian.xbatis.core;

import io.njdi.durian.xbatis.model.*;
import io.njdi.durian.xbatis.model.schema.Database;
import io.njdi.durian.xbatis.model.schema.Table;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class Validator {
  @Autowired
  private Database database;

  enum Context {
    CREATE,
    DELETE,
    PAGE,
    UPDATE
  }

  private void validateTable(String tableName) {
    if (StringUtils.isEmpty(tableName)) {
      throw new RuntimeException("table is empty(null)");
    }

    if (!database.containTable(tableName)) {
      throw new RuntimeException("Table " + tableName + " doesn't exist");
    }
  }

  private void validateOperate(String tableName, Context context) {
    if (Objects.isNull(context)) {
      return;
    }

    Table table = database.getTable(tableName);

    switch (context) {
      case CREATE -> {
        if (!table.isCreate()) {
          throw new RuntimeException(
                  "Table " + tableName + " doesn't support create");
        }
      }

      case DELETE -> {
        if (!table.isDelete()) {
          throw new RuntimeException(
                  "Table " + tableName + " doesn't support delete");
        }
      }

      case PAGE -> {
        if (!table.isPage()) {
          throw new RuntimeException(
                  "Table " + tableName + " doesn't support page");
        }
      }

      case UPDATE -> {
        if (!table.isUpdate()) {
          throw new RuntimeException(
                  "Table " + tableName + " doesn't support update");
        }
      }
    }
  }

  private void validatePairs(String tableName, List<Pair<?>> pairs,
                             Context context) {
    if (!(context == Context.CREATE || context == Context.UPDATE)) {
      return;
    }

    if (CollectionUtils.isEmpty(pairs)) {
      throw new RuntimeException("Create/Update must have pairs");
    }

    Table table = database.getTable(tableName);

    for (Pair<?> pair : pairs) {
      String name = pair.getName();
      if (!table.containColumn(name)) {
        throw new RuntimeException(
                "Table " + tableName + " doesn't have column " + name);
      }

      Object value = pair.getValue();
      if (Objects.isNull(value)) {
        throw new RuntimeException("Pair " + name + " value is null");
      }

      Class<?> type = table.getColumn(name).getType();
      if (!type.isInstance(value)) {
        throw new RuntimeException(
                "Column " + name + " of table " + tableName + " is of type " + type.getName()
                        + ", but value " + value);
      }
    }
  }

  private void validateFilters(String tableName, List<Filter<?>> filters,
                               Context context) {
    if (context == Context.CREATE || context == null) {
      return;
    }

    Table table = database.getTable(tableName);

    if (CollectionUtils.isEmpty(filters)) {
      switch (context) {
        case DELETE -> {
          if (table.isDeleteMustHaveWhere()) {
            throw new RuntimeException(
                    "Table " + tableName + " delete must have wheres");
          }
        }

        case PAGE -> {
          if (table.isPageMustHaveWhere()) {
            throw new RuntimeException(
                    "Table " + tableName + " page must have wheres");
          }
        }

        case UPDATE -> {
          if (table.isUpdateMustHaveWhere()) {
            throw new RuntimeException(
                    "Table " + tableName + " update must have wheres");
          }
        }
      }

      return;
    }

    List<String> names = table.getColumnNames();

    for (Filter<?> filter : filters) {
      String name = filter.getName();
      if (!names.contains(name)) {
        throw new RuntimeException(
                "Table " + tableName + " doesn't have column " + name);
      }

      Filter.Operator operator = filter.getOperator();
      List<?> values = filter.getValues();

      switch (operator) {
        case IS_NULL, IS_NOT_NULL -> {
          if (CollectionUtils.isNotEmpty(values)) {
            throw new RuntimeException(
                    "Filter is null, is not null values must be empty(null)");
          }
        }

        case EQ, NE, GT, LT, GE, LE, LIKE -> {
          if (CollectionUtils.isEmpty(values) || values.size() != 1) {
            throw new RuntimeException(
                    "Filter =, !=, >, <, >=, <=, like values must be 1");
          }
        }

        case BETWEEN -> {
          if (CollectionUtils.isEmpty(values) || values.size() != 2) {
            throw new RuntimeException(
                    "Filter between...and values must be 2");
          }
        }

        case IN, NOT_IN -> {
          if (Objects.isNull(values)) {
            throw new RuntimeException(
                    "Filter in, not in values must not be null");
          }
        }
      }

      if (CollectionUtils.isEmpty(values)) {
        continue;
      }

      Class<?> type = table.getColumn(name).getType();
      Object value = values.get(0);
      if (!type.isInstance(value)) {
        throw new RuntimeException(
                "Column " + name + " of table " + tableName + " is of type " + type.getName()
                        + ", but value " + value);
      }
    }
  }

  private void validateFields(String tableName, List<Field> fields) {
    if (CollectionUtils.isEmpty(fields)) {
      return;
    }

    Table table = database.getTable(tableName);

    List<String> names = table.getColumnNames();

    for (Field field : fields) {
      String name = field.getName();
      if (StringUtils.isEmpty(name)) {
        throw new RuntimeException(
                "page field name must not be empty(null)");
      }

      if (!names.contains(name)) {
        throw new RuntimeException(
                "Page field name " + name + " doesn't exist");
      }

      String alias = field.getAlias();
      if (Objects.isNull(alias)) {
        continue;
      }

      if (StringUtils.isEmpty(alias)) {
        throw new RuntimeException(
                "Page field alias " + alias + " must not be empty");
      }
    }
  }

  private void validateGroups(String tableName, List<String> groups) {
    if (CollectionUtils.isEmpty(groups)) {
      return;
    }

    List<String> names = database.getTable(tableName).getColumnNames();

    for (String group : groups) {
      if (!names.contains(group)) {
        throw new RuntimeException("Page group " + group + " doesn't exist");
      }
    }
  }

  private void validateOrders(String tableName, List<Order> orders) {
    if (CollectionUtils.isEmpty(orders)) {
      return;
    }

    List<String> names = database.getTable(tableName).getColumnNames();

    for (Order order : orders) {
      String name = order.getName();
      if (!names.contains(name)) {
        throw new RuntimeException("Page order " + name + " doesn't exist");
      }
    }
  }

  private void validateLimit(String tableName, int limit) {
    int max = database.getTable(tableName).getLimit();
    if (limit > max) {
      throw new RuntimeException("Page limit exceeds the threshold");
    }
  }

  public void validate(Create create) {
    validateTable(create.getTable());
    validateOperate(create.getTable(), Context.CREATE);
    validatePairs(create.getTable(), create.getPairs(), Context.CREATE);
  }

  public void validate(Delete delete) {
    validateTable(delete.getTable());
    validateOperate(delete.getTable(), Context.DELETE);
    validateFilters(delete.getTable(), delete.getWheres(), Context.DELETE);
  }

  public void validate(Page page) {
    validateTable(page.getTable());
    validateOperate(page.getTable(), Context.PAGE);
    validateFields(page.getTable(), page.getFields());
    validateFilters(page.getTable(), page.getWheres(), Context.PAGE);
    validateGroups(page.getTable(), page.getGroups());
    validateFilters(page.getTable(), page.getHavings(), null);
    validateOrders(page.getTable(), page.getOrders());
    validateLimit(page.getTable(), page.getLimit());
  }

  public void validate(Update update) {
    validateTable(update.getTable());
    validateOperate(update.getTable(), Context.UPDATE);
    validatePairs(update.getTable(), update.getPairs(), Context.UPDATE);
    validateFilters(update.getTable(), update.getWheres(), Context.UPDATE);
  }
}