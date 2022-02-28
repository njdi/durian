package io.njdi.durian.config.model;

import io.njdi.durian.xbatis.model.schema.Column;
import io.njdi.durian.xbatis.model.schema.Database;
import io.njdi.durian.xbatis.model.schema.Table;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConfigTable extends Table {
  public static final String COL_ID = "id";
  public static final String COL_UID = "uid";
  public static final String COL_NAME = "name";
  public static final String COL_VALUE = "value";

  public ConfigTable(Database database, @Value("${durian.config.table:config}") String table) {
    setName(table);

    Column id = Column.builder()
            .name(COL_ID)
            .type(Integer.class)
            .implicit(true)
            .build();
    Column uid = Column.builder()
            .name(COL_UID)
            .implicit(true)
            .build();
    Column name = Column.builder()
            .name(COL_NAME)
            .implicit(true)
            .build();
    Column value = Column.builder()
            .name(COL_VALUE)
            .implicit(true)
            .build();

    setColumns(List.of(id, uid, name, value));
    setPageMustHaveWhere(false);

    database.addTable(this);
  }
}
