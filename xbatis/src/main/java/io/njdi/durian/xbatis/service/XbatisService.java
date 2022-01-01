package io.njdi.durian.xbatis.service;

import io.njdi.durian.xbatis.model.*;

import java.util.List;
import java.util.Map;

public interface XbatisService {
  List<Integer> creates(Creates creates);

  int create(Create create);

  int deletes(Deletes deletes);

  int delete(Delete delete);

  List<Map<String, Object>> page(Page page);

  int updates(Updates updates);

  int update(Update update);
}
