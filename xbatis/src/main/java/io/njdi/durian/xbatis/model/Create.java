package io.njdi.durian.xbatis.model;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Create {
  private Integer id;
  private String table;
  @Singular
  private List<Pair<?>> pairs;
}
