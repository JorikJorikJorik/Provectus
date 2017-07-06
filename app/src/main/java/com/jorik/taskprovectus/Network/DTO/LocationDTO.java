package com.jorik.taskprovectus.Network.DTO;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocationDTO implements Parcelable{
  @SerializedName("street")
  @Expose
  private String street;

  @SerializedName("city")
  @Expose
  private String city;

  @SerializedName("state")
  @Expose
  private String state;

//  @SerializedName("postcode")
//  @Expose
//  private Integer postcode;

  protected LocationDTO(Parcel in) {
    street = in.readString();
    city = in.readString();
    state = in.readString();
  }

  public static final Creator<LocationDTO> CREATOR = new Creator<LocationDTO>() {
    @Override
    public LocationDTO createFromParcel(Parcel in) {
      return new LocationDTO(in);
    }

    @Override
    public LocationDTO[] newArray(int size) {
      return new LocationDTO[size];
    }
  };

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(street);
    dest.writeString(city);
    dest.writeString(state);
  }

//  public Integer getPostcode() {
//    return postcode;
//  }
//
//  public void setPostcode(Integer postcode) {
//    this.postcode = postcode;
//  }
}
