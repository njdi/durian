package io.njdi.durian.common.util;

import io.njdi.durian.common.Constant;

import java.util.UUID;

public class Uuid {
  public static String get() {
    return UUID.randomUUID().toString().replace(Constant.LINE_THROUGH,
            Constant.EMPTY);
  }
}
