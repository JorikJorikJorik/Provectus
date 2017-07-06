package com.jorik.taskprovectus.Utils;

public class StringUtils {

  private static final int FIRST_LATER = 0;
  private static final int SECOND_LATER = 1;

  public static String firstLatterToUpper(String data) {
    return data.substring(FIRST_LATER, SECOND_LATER).toUpperCase() + data.substring(SECOND_LATER).toLowerCase();
  }

}
