package io.njdi.durian.xbatis.model;

import io.njdi.durian.xbatis.model.where.Where;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Delete implements Serializable {
  private String table;
  @Singular
  private List<Where> wheres;
}
