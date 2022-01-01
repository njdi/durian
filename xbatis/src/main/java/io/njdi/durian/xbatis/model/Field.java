package io.njdi.durian.xbatis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Field {
  private String name;
  private String alias;

  public static FieldBuilder builder() {
    return new FieldBuilder();
  }

  public static class FieldBuilder {
    private String name;
    private String alias;

    FieldBuilder() {
    }

    public FieldBuilder name(String name) {
      this.name = name;
      return this;
    }

    public FieldBuilder alias(String alias) {
      this.alias = alias;
      return this;
    }

    public Field build() {
      return new Field(name, alias);
    }

    public String toString() {
      return "Field.FieldBuilder(name=" + this.name + ", alias=" + this.alias + ")";
    }
  }
}
