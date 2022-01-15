package io.njdi.durian.xbatis.model;

import io.njdi.durian.xbatis.model.where.Where;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Delete {
  private String table;
  @Singular
  private List<Where> wheres;
}
