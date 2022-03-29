package io.njdi.durian.xbatis.model;

import io.njdi.durian.xbatis.model.where.Where;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Count implements Serializable {
  private String table;
  @Singular
  private List<Where> wheres;
}
