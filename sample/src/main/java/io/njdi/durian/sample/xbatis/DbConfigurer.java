package io.njdi.durian.sample.xbatis;

import io.njdi.durian.xbatis.model.schema.Column;
import io.njdi.durian.xbatis.model.schema.Database;
import io.njdi.durian.xbatis.model.schema.Table;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbConfigurer {
  public static final String TABLE_MYTABLE = "myTable";

  public static final String COLUMN_MYTABLE_ID = "id";
  public static final String COLUMN_MYTABLE_COL_ONE = "colOne";
  public static final String COLUMN_MYTABLE_COL_TWO = "colTwo";

  public Table createTable() {
    Column id = Column.builder().name(COLUMN_MYTABLE_ID).type(Integer.class).implicit(true).create(true).build();
    Column colOne = Column.builder().name(COLUMN_MYTABLE_COL_ONE).alias("col1").implicit(true).build();
    Column colTwo = Column.builder().name(COLUMN_MYTABLE_COL_TWO).alias("col2").implicit(true).build();

    return Table.builder()
            .name(TABLE_MYTABLE)
            .alias("mytable")
            .column(id)
            .column(colOne)
            .column(colTwo)
            .build();
  }

  @Bean
  public Database createDatabase() {
    Table myTable = createTable();

    return Database.builder()
            .table(myTable)
            .build();
  }
}
