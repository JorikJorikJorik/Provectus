package com.jorik.taskprovectus.Model.Enum;

public enum PermissionState {
  NONE(0),
  FIRST(1),
  DENIED(2),
  DONT_ASK(3),
  CONFIRM(4);

  private int value;

  PermissionState(int value) {
    this.value = value;
  }

  public static PermissionState fromValue(int value) {
    for (PermissionState item : values()) {
      if (item.getValue() == value) {
        return item;
      }
    }
    return NONE;
  }

  public int getValue() {
    return value;
  }
}
