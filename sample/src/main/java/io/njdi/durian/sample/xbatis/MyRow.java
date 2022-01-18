package io.njdi.durian.sample.xbatis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyRow {
  private Integer id;
  private String colOne;
  private String colTwo;
}
