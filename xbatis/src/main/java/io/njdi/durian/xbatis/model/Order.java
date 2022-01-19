package io.njdi.durian.xbatis.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Order extends Expression implements Serializable {
  private String name;

  @Builder.Default
  private Sort sort = Sort.ASC;

  public enum Sort {
    ASC,
    DESC
  }
}
