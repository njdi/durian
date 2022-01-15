package io.njdi.durian.xbatis.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@AllArgsConstructor
@SuperBuilder
public class Order extends Expression {
  private String name;

  @Builder.Default
  private Sort sort = Sort.ASC;

  public enum Sort {
    ASC,
    DESC
  }
}
