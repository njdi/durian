package io.njdi.durian.xbatis.model.schema;

import lombok.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Table {
  private String name;
  private String alias;
  @Singular
  private List<Column> columns;

  @Builder.Default
  private int limit = Integer.MAX_VALUE;

  @Builder.Default
  private boolean create = true;
  @Builder.Default
  private boolean delete = true;
  @Builder.Default
  private boolean page = true;
  @Builder.Default
  private boolean update = true;

  @Builder.Default
  private boolean deleteMustHaveWhere = true;
  @Builder.Default
  private boolean pageMustHaveWhere = true;
  @Builder.Default
  private boolean updateMustHaveWhere = true;

  public void validate() {
    if (StringUtils.isEmpty(name)) {
      throw new RuntimeException("Table name is empty(null)");
    }

    if (Objects.nonNull(alias) && StringUtils.isEmpty(alias)) {
      throw new RuntimeException("Table " + name + " alias is null");
    }

    if (CollectionUtils.isEmpty(columns)) {
      throw new RuntimeException("Table " + name + " columns is empty(null)");
    }

    for (Column column : columns) {
      if (Objects.isNull(column)) {
        throw new RuntimeException("Table " + name
                + " contains empty(null) column");
      }

      column.validate();
    }
  }

  public List<String> getImplicitColumnNames() {
    return columns.stream()
            .filter(Column::isImplicit)
            .map(Column::getName)
            .collect(Collectors.toList());
  }

  public List<String> getColumnNames() {
    return columns.stream()
            .map(Column::getName)
            .collect(Collectors.toList());
  }

  public List<String> getColumnAliases() {
    return columns.stream()
            .map(Column::getAlias)
            .collect(Collectors.toList());
  }

  public Map<String, String> getColumnNameToAliases() {
    return columns.stream()
            .filter(column ->
                    StringUtils.isNotEmpty(column.getName())
                            && StringUtils.isNotEmpty(column.getAlias()))
            .collect(Collectors.toMap(Column::getName, Column::getAlias));
  }

  public boolean containColumn(String columnName) {
    if (StringUtils.isEmpty(columnName)) {
      return false;
    }

    return columns.stream().anyMatch(column -> column.getName().equals(columnName));
  }

  public Column getColumn(String columnName) {
    if (StringUtils.isEmpty(columnName)) {
      return null;
    }

    return columns.stream()
            .filter(column -> columnName.equals(column.getName()))
            .findFirst()
            .orElse(null);
  }
}
