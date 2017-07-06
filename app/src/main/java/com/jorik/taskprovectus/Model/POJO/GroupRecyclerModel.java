package com.jorik.taskprovectus.Model.POJO;

import com.jorik.taskprovectus.View.Adapter.BaseAdapter;

public class GroupRecyclerModel<T extends BaseAdapter> {

  private String subHeader;
  private Integer iconGroup;
  private T adapterGroup;

  public String getSubHeader() {
    return subHeader;
  }

  public void setSubHeader(String subHeader) {
    this.subHeader = subHeader;
  }

  public Integer getIconGroup() {
    return iconGroup;
  }

  public void setIconGroup(Integer iconGroup) {
    this.iconGroup = iconGroup;
  }

  public T getAdapterGroup() {
    return adapterGroup;
  }

  public void setAdapterGroup(T adapterGroup) {
    this.adapterGroup = adapterGroup;
  }
}
