package com.jorik.taskprovectus.Network.DTO;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IdDTO implements Parcelable {

  @SerializedName("name")
  @Expose
  private String name;

  @SerializedName("value")
  @Expose
  private String value;

  protected IdDTO(Parcel in) {
    name = in.readString();
    value = in.readString();
  }

  public static final Creator<IdDTO> CREATOR = new Creator<IdDTO>() {
    @Override
    public IdDTO createFromParcel(Parcel in) {
      return new IdDTO(in);
    }

    @Override
    public IdDTO[] newArray(int size) {
      return new IdDTO[size];
    }
  };

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(name);
    dest.writeString(value);
  }
}
