package io.njdi.durian.sample.xbatis;

import io.njdi.durian.xbatis.model.schema.Column;
import io.njdi.durian.xbatis.model.schema.Database;
import io.njdi.durian.xbatis.model.schema.Table;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class MyTable extends Table {
  public static final String TBL_NAME = "mytable";

  public static final String COL_ID = "id";
  public static final String COL_ONE = "colOne";
  public static final String COL_TWO = "colTwo";

  public MyTable(Database database) {
    setName(TBL_NAME);

    Column id = Column.builder()
            .name(COL_ID)
            .type(Integer.class)
            .implicit(true)
            .create(true)
            .build();
    Column colOne = Column.builder()
            .name(COL_ONE)
            .alias("col1")
            .implicit(true)
            .build();
    Column colTwo = Column.builder()
            .name(COL_TWO)
            .alias("col2")
            .implicit(true)
            .build();

    setColumns(Arrays.asList(id, colOne, colTwo));

    database.addTable(this);
  }
}
