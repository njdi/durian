package io.njdi.durian.xbatis.model.where;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Filter extends Where {
  public Filter(String name, Operator operator, Object value) {
    this.name = name;
    this.operator = operator;
    this.values = List.of(value);
  }

  static boolean isFilter(Where where) {
    return where instanceof Filter;
  }

  static boolean isOrFilter(Where where) {
    return where instanceof OrFilter;
  }

  private String name;
  @Builder.Default
  private Operator operator = Operator.EQ;
  @Singular
  private List<Object> values;

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
