package io.njdi.durian.xbatis.model;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Updates {
  @Singular
  private List<Update> updates;
}
