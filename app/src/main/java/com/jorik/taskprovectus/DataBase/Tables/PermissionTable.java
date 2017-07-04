package com.jorik.taskprovectus.DataBase.Tables;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class PermissionTable extends RealmObject{

  @PrimaryKey
  private String namePermission;
  private int permissionStateValue;

  public String getNamePermission() {
    return namePermission;
  }

  public void setNamePermission(String namePermission) {
    this.namePermission = namePermission;
  }

  public int getPermissionStateValue() {
    return permissionStateValue;
  }

  public void setPermissionStateValue(int permissionStateValue) {
    this.permissionStateValue = permissionStateValue;
  }
}
