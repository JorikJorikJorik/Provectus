package com.jorik.taskprovectus.Network.DTO;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDataDTO implements Parcelable {

  @SerializedName("gender")
  @Expose
  private String gender;

  @SerializedName("name")
  @Expose
  private NameDTO name;

  @SerializedName("location")
  @Expose
  private LocationDTO location;

  @SerializedName("email")
  @Expose
  private String email;

  @SerializedName("login")
  @Expose
  private LoginDTO login;

  @SerializedName("dob")
  @Expose
  private String dob;

  @SerializedName("registered")
  @Expose
  private String registered;

  @SerializedName("phone")
  @Expose
  private String phone;

  @SerializedName("cell")
  @Expose
  private String cell;

  @SerializedName("id")
  @Expose
  private IdDTO id;

  @SerializedName("picture")
  @Expose
  private PictureDTO picture;

  @SerializedName("nat")
  @Expose
  private String nat;

  public UserDataDTO() {
  }

  protected UserDataDTO(Parcel in) {
    gender = in.readString();
    name = in.readParcelable(NameDTO.class.getClassLoader());
    location = in.readParcelable(LocationDTO.class.getClassLoader());
    email = in.readString();
    login = in.readParcelable(LoginDTO.class.getClassLoader());
    dob = in.readString();
    registered = in.readString();
    phone = in.readString();
    cell = in.readString();
    id = in.readParcelable(IdDTO.class.getClassLoader());
    picture = in.readParcelable(PictureDTO.class.getClassLoader());
    nat = in.readString();
  }

  public static final Creator<UserDataDTO> CREATOR = new Creator<UserDataDTO>() {
    @Override
    public UserDataDTO createFromParcel(Parcel in) {
      return new UserDataDTO(in);
    }

    @Override
    public UserDataDTO[] newArray(int size) {
      return new UserDataDTO[size];
    }
  };

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public NameDTO getName() {
    return name;
  }

  public void setName(NameDTO name) {
    this.name = name;
  }

  public LocationDTO getLocation() {
    return location;
  }

  public void setLocation(LocationDTO location) {
    this.location = location;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public LoginDTO getLogin() {
    return login;
  }

  public void setLogin(LoginDTO login) {
    this.login = login;
  }

  public String getDob() {
    return dob;
  }

  public void setDob(String dob) {
    this.dob = dob;
  }

  public String getRegistered() {
    return registered;
  }

  public void setRegistered(String registered) {
    this.registered = registered;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getCell() {
    return cell;
  }

  public void setCell(String cell) {
    this.cell = cell;
  }

  public IdDTO getId() {
    return id;
  }

  public void setId(IdDTO id) {
    this.id = id;
  }

  public PictureDTO getPicture() {
    return picture;
  }

  public void setPicture(PictureDTO picture) {
    this.picture = picture;
  }

  public String getNat() {
    return nat;
  }

  public void setNat(String nat) {
    this.nat = nat;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(gender);
    dest.writeParcelable(name, flags);
    dest.writeParcelable(location, flags);
    dest.writeString(email);
    dest.writeParcelable(login, flags);
    dest.writeString(dob);
    dest.writeString(registered);
    dest.writeString(phone);
    dest.writeString(cell);
    dest.writeParcelable(id, flags);
    dest.writeParcelable(picture, flags);
    dest.writeString(nat);
  }
}
