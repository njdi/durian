package io.njdi.durian.common.util;

import io.njdi.durian.common.Constant;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class Datetime {
  public static final Pattern DATETIME_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$");

  public static String datetime() {
    return LocalDateTime.now().format(DateTimeFormatter.ofPattern(Constant.LOCAL_DATE_TIME));
  }

  public static String todayStartTime() {
    return LocalDateTime.now().format(DateTimeFormatter.ofPattern(Constant.LOCAL_DATE));
  }

  public static boolean isDatetime(String datetime) {
    if (StringUtils.isEmpty(datetime)) {
      return false;
    }

    return DATETIME_PATTERN.matcher(datetime).matches();
  }
}
