package com.jorik.taskprovectus.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtils {

  public static final String PARSE_DATE_GSON = "yyyy-MM-dd'T'HH:mm:ss";
  private static final String UTC = "UTC";

  public static Date parseDate(String date, String format) {
    Date parseDate = new Date();
    try {
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.ENGLISH);
      parseDate = simpleDateFormat.parse(date);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return parseDate;
  }

  public static String formatDate(Date date, String format) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
    simpleDateFormat.setTimeZone(TimeZone.getTimeZone(UTC));
    return simpleDateFormat.format(date);
  }

  public static String changeTypeFormated(String date, String oldFormat, String newFormat){
   return formatDate(parseDate(date, oldFormat), newFormat);
  }

}
