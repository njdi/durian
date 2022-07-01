package io.njdi.durian.xbatis.model.schema;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class Column implements Checker {
  private String name;
  private String alias;
  private Class<?> type = String.class;

  private boolean implicit = true;

  private boolean create = true;
  private boolean select = true;
  private boolean update = true;

  public Column(String name) {
    this.name = name;
  }

  public Column(String name, Class<?> type) {
    this.name = name;
    this.type = type;
  }

  public Column(String name, String alias) {
    this.name = name;
    this.alias = alias;
  }

  public Column(String name, String alias, Class<?> type) {
    this.name = name;
    this.alias = alias;
    this.type = type;
  }

  public Column(String name, String alias, Class<?> type, boolean implicit, boolean create, boolean select,
                boolean update) {
    this.name = name;
    this.alias = alias;
    this.type = type;
    this.implicit = implicit;
    this.create = create;
    this.select = select;
    this.update = update;
  }

  public Column() {
  }

  private static Class<?> $default$type() {
    return String.class;
  }

  private static boolean $default$implicit() {
    return true;
  }

  private static boolean $default$create() {
    return true;
  }

  private static boolean $default$select() {
    return true;
  }

  private static boolean $default$update() {
    return true;
  }

  public static ColumnBuilder builder() {
    return new ColumnBuilder();
  }

  @Override
  public void check() {
    if (StringUtils.isEmpty(name)) {
      throw new RuntimeException("Column name is empty(null)");
    }

    if (Objects.nonNull(alias) && StringUtils.isEmpty(alias)) {
      throw new RuntimeException("Column " + name + " alias is null");
    }

    if (Objects.isNull(type)) {
      throw new RuntimeException("Column " + name + " type is null");
    }
  }

  public String getName() {
    return this.name;
  }

  public String getAlias() {
    return this.alias;
  }

  public Class<?> getType() {
    return this.type;
  }

  public boolean isImplicit() {
    return this.implicit;
  }

  public boolean isCreate() {
    return this.create;
  }

  public boolean isSelect() {
    return this.select;
  }

  public boolean isUpdate() {
    return this.update;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAlias(String alias) {
    this.alias = alias;
  }

  public void setType(Class<?> type) {
    this.type = type;
  }

  public void setImplicit(boolean implicit) {
    this.implicit = implicit;
  }

  public void setCreate(boolean create) {
    this.create = create;
  }

  public void setSelect(boolean select) {
    this.select = select;
  }

  public void setUpdate(boolean update) {
    this.update = update;
  }

  public boolean equals(final Object o) {
    if (o == this) return true;
    if (!(o instanceof Column)) return false;
    final Column other = (Column) o;
    if (!other.canEqual((Object) this)) return false;
    final Object this$name = this.getName();
    final Object other$name = other.getName();
    if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
    final Object this$alias = this.getAlias();
    final Object other$alias = other.getAlias();
    if (this$alias == null ? other$alias != null : !this$alias.equals(other$alias)) return false;
    final Object this$type = this.getType();
    final Object other$type = other.getType();
    if (this$type == null ? other$type != null : !this$type.equals(other$type)) return false;
    if (this.isImplicit() != other.isImplicit()) return false;
    if (this.isCreate() != other.isCreate()) return false;
    if (this.isSelect() != other.isSelect()) return false;
    if (this.isUpdate() != other.isUpdate()) return false;
    return true;
  }

  protected boolean canEqual(final Object other) {
    return other instanceof Column;
  }

  public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    final Object $name = this.getName();
    result = result * PRIME + ($name == null ? 43 : $name.hashCode());
    final Object $alias = this.getAlias();
    result = result * PRIME + ($alias == null ? 43 : $alias.hashCode());
    final Object $type = this.getType();
    result = result * PRIME + ($type == null ? 43 : $type.hashCode());
    result = result * PRIME + (this.isImplicit() ? 79 : 97);
    result = result * PRIME + (this.isCreate() ? 79 : 97);
    result = result * PRIME + (this.isSelect() ? 79 : 97);
    result = result * PRIME + (this.isUpdate() ? 79 : 97);
    return result;
  }

  public String toString() {
    return "Column(name=" + this.getName() + ", alias=" + this.getAlias() + ", type=" + this.getType() + ", implicit" +
            "=" + this.isImplicit() + ", create=" + this.isCreate() + ", select=" + this.isSelect() + ", update=" + this.isUpdate() + ")";
  }

  public static class ColumnBuilder {
    private String name;
    private String alias;
    private Class<?> type$value;
    private boolean type$set;
    private boolean implicit$value;
    private boolean implicit$set;
    private boolean create$value;
    private boolean create$set;
    private boolean select$value;
    private boolean select$set;
    private boolean update$value;
    private boolean update$set;

    ColumnBuilder() {
    }

    public ColumnBuilder name(String name) {
      this.name = name;
      return this;
    }

    public ColumnBuilder alias(String alias) {
      this.alias = alias;
      return this;
    }

    public ColumnBuilder type(Class<?> type) {
      this.type$value = type;
      this.type$set = true;
      return this;
    }

    public ColumnBuilder implicit(boolean implicit) {
      this.implicit$value = implicit;
      this.implicit$set = true;
      return this;
    }

    public ColumnBuilder create(boolean create) {
      this.create$value = create;
      this.create$set = true;
      return this;
    }

    public ColumnBuilder select(boolean select) {
      this.select$value = select;
      this.select$set = true;
      return this;
    }

    public ColumnBuilder update(boolean update) {
      this.update$value = update;
      this.update$set = true;
      return this;
    }

    public Column build() {
      Class<?> type$value = this.type$value;
      if (!this.type$set) {
        type$value = Column.$default$type();
      }
      boolean implicit$value = this.implicit$value;
      if (!this.implicit$set) {
        implicit$value = Column.$default$implicit();
      }
      boolean create$value = this.create$value;
      if (!this.create$set) {
        create$value = Column.$default$create();
      }
      boolean select$value = this.select$value;
      if (!this.select$set) {
        select$value = Column.$default$select();
      }
      boolean update$value = this.update$value;
      if (!this.update$set) {
        update$value = Column.$default$update();
      }
      return new Column(name, alias, type$value, implicit$value, create$value, select$value, update$value);
    }

    public String toString() {
      return "Column.ColumnBuilder(name=" + this.name + ", alias=" + this.alias + ", type$value=" + this.type$value + ", implicit$value=" + this.implicit$value + ", create$value=" + this.create$value + ", select$value=" + this.select$value + ", update$value=" + this.update$value + ")";
    }
  }
}
