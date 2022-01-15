package io.njdi.durian.xbatis.model.where;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrFilter implements Where {
  @Singular
  private List<Filter> filters;
}
