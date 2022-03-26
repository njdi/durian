package io.njoi.durian.common.util;

import io.njdi.durian.common.util.Datetime;
import org.junit.Test;

public class DatetimeTest {
  @Test
  public void datetime() {
    System.out.println(Datetime.datetime());
  }

  @Test
  public void todayStartTime() {
    System.out.println(Datetime.todayStartTime());
  }

  @Test
  public void isDatetime() {
    System.out.println(Datetime.isDatetime(Datetime.datetime()));
  }
}
