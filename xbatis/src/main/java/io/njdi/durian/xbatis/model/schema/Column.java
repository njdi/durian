package io.njdi.durian.xbatis.model.schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Column {
  private String name;
  private String alias;
  @Builder.Default
  private Class<?> type = String.class;

  @Builder.Default
  private boolean implicit = false;

  @Builder.Default
  private boolean create = true;
  @Builder.Default
  private boolean select = true;
  @Builder.Default
  private boolean update = true;

  public void validate() {
    if (StringUtils.isEmpty(name)) {
      throw new RuntimeException("Column name is empty(null)");
    }

    if (Objects.nonNull(alias) && StringUtils.isEmpty(alias)) {
      throw new RuntimeException("Column " + name + " alias is null");
    }

    if (Objects.isNull(type)) {
      throw new RuntimeException("Column " + name + " type is null");
    }
  }
}
