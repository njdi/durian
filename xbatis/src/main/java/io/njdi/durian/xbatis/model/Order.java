package io.njdi.durian.xbatis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
  private String name;

  @Builder.Default
  private Sort sort = Sort.ASC;

  public enum Sort {
    ASC,
    DESC
  }
}
