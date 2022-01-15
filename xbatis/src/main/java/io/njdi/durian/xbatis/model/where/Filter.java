package io.njdi.durian.xbatis.model.where;

import io.njdi.durian.xbatis.model.Expression;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Singular;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@AllArgsConstructor
@SuperBuilder
public class Filter extends Expression implements Where {
  static boolean isFilter(Where where) {
    return where instanceof Filter;
  }

  static boolean isAndFilter(Where where) {
    return where instanceof AndFilter;
  }

  static boolean isOrFilter(Where where) {
    return where instanceof OrFilter;
  }

  static boolean isNotFilter(Where where) {
    return where instanceof NotFilter;
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
