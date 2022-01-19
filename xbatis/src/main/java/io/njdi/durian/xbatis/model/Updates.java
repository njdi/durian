package io.njdi.durian.xbatis.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Updates implements Serializable {
  @Singular
  private List<Update> updates;
}
