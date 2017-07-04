package com.jorik.taskprovectus.Model.POJO;

import com.jorik.taskprovectus.Model.Enum.ErrorNetworkType;

public class ErrorModel
{

  private ErrorNetworkType mErrorNetworkType;
  private int code;
  private String message;

  public ErrorNetworkType getErrorNetworkType() {
    return mErrorNetworkType;
  }

  public void setErrorNetworkType(ErrorNetworkType errorNetworkType) {
    mErrorNetworkType = errorNetworkType;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
