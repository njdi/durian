package io.njdi.durian.xbatis.model;

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
  private List<Filter<?>> wheres;
  @Singular
  private List<String> groups;
  @Singular
  private List<Filter<?>> havings;
  @Singular
  private List<Order> orders;

  @Builder.Default
  private Integer limit = Integer.MAX_VALUE;
  @Builder.Default
  private Integer offset = 0;
}
