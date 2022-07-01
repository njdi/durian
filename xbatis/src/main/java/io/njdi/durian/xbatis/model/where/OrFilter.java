package io.njdi.durian.xbatis.model.where;

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
@SuperBuilder
public class OrFilter extends Where {
  @Singular
  private List<Filter> filters;

  public OrFilter(List<Filter> filters) {
    this.filters = filters;
  }

  public OrFilter(Filter... filters) {
    this(List.of(filters));
  }
}
