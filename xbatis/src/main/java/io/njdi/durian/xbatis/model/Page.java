package io.njdi.durian.xbatis.model;

import io.njdi.durian.xbatis.model.where.Where;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Page {
  @Singular
  private List<Field> fields;
  private String table;
  @Singular
  private List<Where> wheres;
  @Singular
  private List<String> groups;
  @Singular
  private List<Where> havings;
  @Singular
  private List<Order> orders;

  @Builder.Default
  private Integer limit = Integer.MAX_VALUE;
  @Builder.Default
  private Integer offset = 0;
}
