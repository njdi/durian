package io.njdi.durian.xbatis.model.schema;

import lombok.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Database {
  @Singular
  private List<Table> tables;

  public void validate() {
    if (CollectionUtils.isEmpty(tables)) {
      throw new RuntimeException("Database tables is empty(null)");
    }

    for (Table table : tables) {
      if (Objects.isNull(table)) {
        throw new RuntimeException("Database contains empty(null) table");
      }

      table.validate();
    }
  }

  public boolean containTable(String tableName) {
    if (StringUtils.isEmpty(tableName) || CollectionUtils.isEmpty(tables)) {
      return false;
    }

    return tables.stream().anyMatch(table -> table.getName().equals(tableName));
  }

  public Table getTable(String tableName) {
    if (StringUtils.isEmpty(tableName) || CollectionUtils.isEmpty(tables)) {
      return null;
    }

    return tables.stream()
            .filter(table ->
                    StringUtils.isNotEmpty(table.getName())
                            && table.getName().equals(tableName))
            .findFirst()
            .orElse(null);
  }

  public Map<String, String> getTableAliases() {
    if (CollectionUtils.isEmpty(tables)) {
      return Collections.emptyMap();
    }

    return tables.stream()
            .filter(table ->
                    StringUtils.isNotEmpty(table.getName())
                            && StringUtils.isNotEmpty(table.getAlias()))
            .collect(Collectors.toMap(Table::getName, Table::getAlias));
  }
}
