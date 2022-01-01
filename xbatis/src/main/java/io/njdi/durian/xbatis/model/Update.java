package io.njdi.durian.xbatis.model;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Update {
  @Singular
  private List<Pair<?>> pairs;
  private String table;
  @Singular
  private List<Filter<?>> wheres;
}
