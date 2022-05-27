package io.njoi.durian.common.util;

import io.njdi.durian.common.util.Datetime;
import org.junit.Test;

public class DatetimeTest {
  @Test
  public void datetime() {
    System.out.println(Datetime.datetime());
  }

  @Test
  public void dayStartTime() {
    System.out.println(Datetime.dayStartTime(Datetime.datetime()));
  }

  @Test
  public void dayEndTime() {
    System.out.println(Datetime.dayEndTime(Datetime.datetime()));
  }

  @Test
  public void todayStartTime() {
    System.out.println(Datetime.todayStartTime());
  }

  @Test
  public void todayEndTime() {
    System.out.println(Datetime.todayEndTime());
  }

  @Test
  public void tomorrowStartTime() {
    System.out.println(Datetime.tomorrowStartTime());
  }

  @Test
  public void tomorrowEndTime() {
    System.out.println(Datetime.tomorrowEndTime());
  }

  @Test
  public void thisWeekStartTime() {
    System.out.println(Datetime.thisWeekStartTime());
  }

  @Test
  public void thisWeekEndTime() {
    System.out.println(Datetime.thisWeekEndTime());
  }

  @Test
  public void nextWeekStartTime() {
    System.out.println(Datetime.nextWeekStartTime());
  }

  @Test
  public void nextWeekEndTime() {
    System.out.println(Datetime.nextWeekEndTime());
  }

  @Test
  public void isDatetime() {
    System.out.println(Datetime.isDatetime(Datetime.datetime()));
  }
}
