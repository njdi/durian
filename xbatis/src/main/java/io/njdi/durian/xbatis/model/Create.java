package io.njdi.durian.xbatis.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Create implements Serializable {
  private Integer id;
  private String table;
  @Singular
  private List<Pair<?>> pairs;

  @Builder.Default
  private boolean update = false;
}
