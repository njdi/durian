package io.njdi.durian.xbatis.core;

import io.njdi.durian.xbatis.model.Create;
import io.njdi.durian.xbatis.model.Delete;
import io.njdi.durian.xbatis.model.Page;
import io.njdi.durian.xbatis.model.Update;

import java.util.List;
import java.util.Map;

public interface XbatisMapper {
  int create(Create create);

  int delete(Delete delete);

  List<Map<String, Object>> page(Page page);

  int update(Update update);
}
