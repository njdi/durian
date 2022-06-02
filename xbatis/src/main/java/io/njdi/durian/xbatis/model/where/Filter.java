package io.njdi.durian.xbatis.model.where;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Filter extends Where {
  public Filter(String name, Operator operator, String value) {
    this(name, operator, List.of(value));
  }

  public Filter(String name, Operator operator, String[] values) {
    this(name, operator, Arrays.stream(values).collect(Collectors.toList()));
  }

  public Filter(String name, Operator operator, int value) {
    this(name, operator, List.of(value));
  }

  public Filter(String name, Operator operator, int[] values) {
    this(name, operator, Arrays.stream(values).boxed().collect(Collectors.toList()));
  }

  public Filter(String name, Operator operator, long value) {
    this(name, operator, List.of(value));
  }

  public Filter(String name, Operator operator, long[] values) {
    this(name, operator, Arrays.stream(values).boxed().collect(Collectors.toList()));
  }

  public Filter(String name, Operator operator, double value) {
    this(name, operator, List.of(value));
  }

  public Filter(String name, Operator operator, double[] values) {
    this(name, operator, Arrays.stream(values).boxed().collect(Collectors.toList()));
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
