package com.jorik.taskprovectus.Network.DTO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class RandomResultsDTO {

  @SerializedName("results")
  @Expose
  private List<UserDataDTO> results;

  @SerializedName("info")
  @Expose
  private InfoDTO info;

  public List<UserDataDTO> getResults() {
    return results;
  }

  public void setResults(List<UserDataDTO> results) {
    this.results = results;
  }

  public InfoDTO getInfo() {
    return info;
  }

  public void setInfo(InfoDTO info) {
    this.info = info;
  }
}
