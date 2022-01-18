package io.njdi.durian.sample.xbatis;

import io.njdi.durian.xbatis.XbatisManager;
import io.njdi.durian.xbatis.model.Create;
import io.njdi.durian.xbatis.model.Creates;
import io.njdi.durian.xbatis.model.Delete;
import io.njdi.durian.xbatis.model.Field;
import io.njdi.durian.xbatis.model.Order;
import io.njdi.durian.xbatis.model.Page;
import io.njdi.durian.xbatis.model.Pair;
import io.njdi.durian.xbatis.model.Update;
import io.njdi.durian.xbatis.model.where.Filter;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;
import java.util.Map;

@SpringBootApplication
@ComponentScan({"io.njdi.durian.sample.xbatis", "io.njdi.durian.xbatis"})
@MapperScan(basePackages = {"io.njdi.durian.xbatis.core"})
@Slf4j
public class Main implements CommandLineRunner {
  @Autowired
  private XbatisManager xbatisManager;

  public void create() {
    String table = DbConfigurer.TABLE_MYTABLE;

    Pair<String> colOne = Pair.<String>builder()
            .name(DbConfigurer.COLUMN_MYTABLE_COL_ONE)
            .value("a")
            .build();
    Pair<String> colTwo = Pair.<String>builder()
            .name(DbConfigurer.COLUMN_MYTABLE_COL_TWO)
            .value("b")
            .build();

    Create create = Create.builder()
            .pair(colOne)
            .pair(colTwo)
            .table(table)
            .build();

    int id = xbatisManager.create(create);

    log.info("id: {}", id);
    log.info("id: {}", create.getId());
  }

  public void creates() {
    String table = DbConfigurer.TABLE_MYTABLE;

    Pair<String> colOne = Pair.<String>builder()
            .name(DbConfigurer.COLUMN_MYTABLE_COL_ONE)
            .value("a")
            .build();
    Pair<String> colTwo = Pair.<String>builder()
            .name(DbConfigurer.COLUMN_MYTABLE_COL_TWO)
            .value("b")
            .build();

    Create create = Create.builder()
            .pair(colOne)
            .pair(colTwo)
            .table(table)
            .build();

    Pair<String> colOne2 = Pair.<String>builder()
            .name(DbConfigurer.COLUMN_MYTABLE_COL_ONE)
            .value("c")
            .build();
    Pair<String> colTwo2 = Pair.<String>builder()
            .name(DbConfigurer.COLUMN_MYTABLE_COL_TWO)
            .value("d")
            .build();

    Create create2 = Create.builder()
            .pair(colOne2)
            .pair(colTwo2)
            .table(table)
            .build();

    Creates creates = Creates.builder()
            .create(create)
            .create(create2)
            .build();

    List<Integer> ids = xbatisManager.creates(creates);
    log.info("ids: {}", ids);
  }

  public void delete() {
    String table = DbConfigurer.TABLE_MYTABLE;

    Filter colOne = Filter.builder()
            .name(DbConfigurer.COLUMN_MYTABLE_COL_ONE)
            .operator(Filter.Operator.EQ)
            .value("a")
            .build();

    Delete delete = Delete.builder()
            .table(table)
            .where(colOne)
            .build();

    int rows = xbatisManager.delete(delete);
    log.info("rows: {}", rows);
  }

  public void page() {
    String table = DbConfigurer.TABLE_MYTABLE;

    Filter filter = Filter.builder()
            .name(DbConfigurer.COLUMN_MYTABLE_COL_ONE)
            .operator(Filter.Operator.IS_NOT_NULL)
            .build();

    Order order = Order.builder()
            .name(DbConfigurer.COLUMN_MYTABLE_COL_ONE)
            .sort(Order.Sort.DESC)
            .build();

    Page page = Page.builder()
            .table(table)
            .where(filter)
            .build();

    List<Map<String, Object>> rows = xbatisManager.page(page);
    log.info("rows: {}", rows);

    List<MyRow> rows2 = xbatisManager.page(page, MyRow.class);
    log.info("rows: {}", rows2);
  }

  public void page2() {
    String table = DbConfigurer.TABLE_MYTABLE;

    Filter filter = Filter.builder()
            .name(DbConfigurer.COLUMN_MYTABLE_COL_ONE)
            .operator(Filter.Operator.IS_NOT_NULL)
            .build();

    Order order = Order.builder()
            .name(DbConfigurer.COLUMN_MYTABLE_COL_ONE)
            .sort(Order.Sort.DESC)
            .build();

    Page page = Page.builder()
            .table(table)
            .where(filter)
            .build();

    List<MyRow> rows = xbatisManager.page(page, MyRow.class);
    log.info("rows: {}", rows);
  }

  public void id() {
    String table = DbConfigurer.TABLE_MYTABLE;

    int id = 106;

    MyRow myRow = xbatisManager.id(table, id, MyRow.class);
    log.info("id: {}", myRow);
  }

  public void id2() {
    String table = DbConfigurer.TABLE_MYTABLE;

    String name = "id";
    Integer value = 106;

    MyRow myRow = xbatisManager.id(table, name, value, MyRow.class);
    log.info("id: {}", myRow);
  }

  public void update() {
    String table = DbConfigurer.TABLE_MYTABLE;

    Filter filter = Filter.builder()
            .name(DbConfigurer.COLUMN_MYTABLE_COL_ONE)
            .operator(Filter.Operator.EQ)
            .value("a")
            .build();

    Pair<String> pair = Pair.<String>builder()
            .name(DbConfigurer.COLUMN_MYTABLE_COL_TWO)
            .value("c")
            .build();

    Update update = Update.builder()
            .table(table)
            .pair(pair)
            .where(filter)
            .build();

    int rows = xbatisManager.update(update);
    log.info("rows: {}", rows);
  }

  public void custom() {
    String table = DbConfigurer.TABLE_MYTABLE;

    Field field = Field.builder()
            .name("UPPER(col1)")
            .alias("colOne")
            .expr(true)
            .build();

    Filter filter = Filter.builder()
            .name(DbConfigurer.COLUMN_MYTABLE_COL_ONE)
            .operator(Filter.Operator.IS_NOT_NULL)
            .build();

    Page page = Page.builder()
            .field(field)
            .table(table)
            .where(filter)
            .build();

    List<Map<String, Object>> rows = xbatisManager.page(page);
    log.info("rows: {}", rows);
  }

  @Override
  public void run(String... args) {
    custom();
  }

  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }
}
