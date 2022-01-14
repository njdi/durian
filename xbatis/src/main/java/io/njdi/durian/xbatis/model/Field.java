package io.njdi.durian.xbatis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Field {
  private String name;
  private String alias;

  @Builder.Default
  private boolean expr = false;
}
