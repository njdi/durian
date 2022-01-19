package io.njdi.durian.xbatis.model;

import io.njdi.durian.xbatis.model.where.Where;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Update implements Serializable {
  @Singular
  private List<Pair<?>> pairs;
  private String table;
  @Singular
  private List<Where> wheres;
}
