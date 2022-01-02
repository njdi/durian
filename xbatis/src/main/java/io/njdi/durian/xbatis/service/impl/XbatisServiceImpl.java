package io.njdi.durian.xbatis.service.impl;

import io.njdi.durian.common.util.Bean;
import io.njdi.durian.xbatis.mapper.XbatisMapper;
import io.njdi.durian.xbatis.model.*;
import io.njdi.durian.xbatis.model.schema.Database;
import io.njdi.durian.xbatis.model.schema.Transformer;
import io.njdi.durian.xbatis.model.schema.Validator;
import io.njdi.durian.xbatis.service.XbatisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class XbatisServiceImpl implements XbatisService {
  private final Database database;

  @Autowired
  public XbatisServiceImpl(Database database) {
    this.database = database;
    this.database.validate();
  }

  @Autowired
  private Transformer transformer;
  @Autowired
  private Validator validator;

  @Autowired
  private XbatisMapper commonDao;

  @Override
  public List<Integer> creates(Creates creates) {
    List<Integer> ids = new ArrayList<>();

    for (Create create : creates.getCreates()) {
      ids.add(create(create));
    }

    return ids;
  }

  @Override
  public int create(Create create) {
    validator.validate(create);
    create = transformer.transform(create);
    log.info("create(transformed): {}", create);

    commonDao.create(create);

    return create.getId();
  }

  @Override
  public int deletes(Deletes deletes) {
    int affectedRows = 0;

    for (Delete delete : deletes.getDeletes()) {
      affectedRows += delete(delete);
    }

    return affectedRows;
  }

  @Override
  public int delete(Delete delete) {
    validator.validate(delete);
    delete = transformer.transform(delete);
    log.info("delete(transformed): {}", delete);

    return commonDao.delete(delete);
  }

  @Override
  public List<Map<String, Object>> page(Page page) {
    validator.validate(page);
    page = transformer.transform(page);
    log.info("page(transformed): {}", page);

    return commonDao.page(page);
  }

  @Override
  public <T> List<T> page(Page page, Class<T> clazz) {
    return Bean.converts(clazz, page(page));
  }

  @Override
  public int updates(Updates updates) {
    int affectedRows = 0;

    for (Update update : updates.getUpdates()) {
      affectedRows += update(update);
    }

    return affectedRows;
  }

  @Override
  public int update(Update update) {
    validator.validate(update);
    update = transformer.transform(update);
    log.info("delete(transformed): {}", update);

    return commonDao.update(update);
  }
}
