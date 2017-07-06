package com.jorik.taskprovectus.Model.POJO;

public class InfoUserModel {

  private String mainData;
  private String secondaryData;
  private Integer iconAction;

  public InfoUserModel(String mainData, String secondaryData, Integer iconAction) {
    this.mainData = mainData;
    this.secondaryData = secondaryData;
    this.iconAction = iconAction;
  }

  public String getMainData() {
    return mainData;
  }

  public void setMainData(String mainData) {
    this.mainData = mainData;
  }

  public String getSecondaryData() {
    return secondaryData;
  }

  public void setSecondaryData(String secondaryData) {
    this.secondaryData = secondaryData;
  }

  public Integer getIconAction() {
    return iconAction;
  }

  public void setIconAction(Integer iconAction) {
    this.iconAction = iconAction;
  }
}
