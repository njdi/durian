package io.njdi.durian.common.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Bean {
  public static <T> T convert(Class<T> clazz, Map<String, Object> map) {
    try {
      T instance = clazz.getDeclaredConstructor().newInstance();
      BeanUtils.populate(instance, map);

      return instance;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static <T> List<T> converts(Class<T> clazz,
                                     List<Map<String, Object>> maps) {
    return maps.stream()
            .map(map -> convert(clazz, map))
            .collect(Collectors.toList());
  }

  public static <T> T convert(T bean, Map<String, Object> map) {
    try {
      BeanUtils.populate(bean, map);

      return bean;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static <T> List<T> convert(List<T> beans,
                                    List<Map<String, Object>> maps) {
    return IntStream.range(0, beans.size())
            .mapToObj(index -> convert(beans.get(index), maps.get(index)))
            .collect(Collectors.toList());
  }

  public static synchronized void register(Converter converter, Class<?> clazz) {
    Converter instance = ConvertUtils.lookup(clazz);
    if (Objects.nonNull(instance)) {
      return;
    }

    ConvertUtils.register(converter, clazz);
  }
}
