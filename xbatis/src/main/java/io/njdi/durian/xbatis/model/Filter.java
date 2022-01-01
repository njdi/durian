package io.njdi.durian.xbatis.model;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Filter<T> {
  private String name;
  @Builder.Default
  private Operator operator = Operator.EQ;
  @Singular
  private List<T> values;

  public enum Operator {
    EQ,
    NE,
    GT,
    LT,
    GE,
    LE,
    BETWEEN,
    LIKE,

    IN,
    NOT_IN,

    IS_NULL,
    IS_NOT_NULL
  }
}
