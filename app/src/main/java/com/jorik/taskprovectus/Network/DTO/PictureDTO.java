package com.jorik.taskprovectus.Network.DTO;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PictureDTO implements Parcelable {

  @SerializedName("large")
  @Expose
  private String large;

  @SerializedName("medium")
  @Expose
  private String medium;

  @SerializedName("thumbnail")
  @Expose
  private String thumbnail;

  public PictureDTO() {
  }

  protected PictureDTO(Parcel in) {
    large = in.readString();
    medium = in.readString();
    thumbnail = in.readString();
  }

  public static final Creator<PictureDTO> CREATOR = new Creator<PictureDTO>() {
    @Override
    public PictureDTO createFromParcel(Parcel in) {
      return new PictureDTO(in);
    }

    @Override
    public PictureDTO[] newArray(int size) {
      return new PictureDTO[size];
    }
  };

  public String getLarge() {
    return large;
  }

  public void setLarge(String large) {
    this.large = large;
  }

  public String getMedium() {
    return medium;
  }

  public void setMedium(String medium) {
    this.medium = medium;
  }

  public String getThumbnail() {
    return thumbnail;
  }

  public void setThumbnail(String thumbnail) {
    this.thumbnail = thumbnail;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(large);
    dest.writeString(medium);
    dest.writeString(thumbnail);
  }
}
