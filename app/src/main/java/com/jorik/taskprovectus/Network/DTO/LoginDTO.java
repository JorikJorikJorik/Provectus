package com.jorik.taskprovectus.Network.DTO;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginDTO implements Parcelable{

  @SerializedName("username")
  @Expose
  private String username;

  @SerializedName("password")
  @Expose
  private String password;

  @SerializedName("salt")
  @Expose
  private String salt;

  @SerializedName("md5")
  @Expose
  private String md5;

  @SerializedName("sha1")
  @Expose
  private String sha1;

  @SerializedName("sha256")
  @Expose
  private String sha256;

  protected LoginDTO(Parcel in) {
    username = in.readString();
    password = in.readString();
    salt = in.readString();
    md5 = in.readString();
    sha1 = in.readString();
    sha256 = in.readString();
  }

  public static final Creator<LoginDTO> CREATOR = new Creator<LoginDTO>() {
    @Override
    public LoginDTO createFromParcel(Parcel in) {
      return new LoginDTO(in);
    }

    @Override
    public LoginDTO[] newArray(int size) {
      return new LoginDTO[size];
    }
  };

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }

  public String getMd5() {
    return md5;
  }

  public void setMd5(String md5) {
    this.md5 = md5;
  }

  public String getSha1() {
    return sha1;
  }

  public void setSha1(String sha1) {
    this.sha1 = sha1;
  }

  public String getSha256() {
    return sha256;
  }

  public void setSha256(String sha256) {
    this.sha256 = sha256;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(username);
    dest.writeString(password);
    dest.writeString(salt);
    dest.writeString(md5);
    dest.writeString(sha1);
    dest.writeString(sha256);
  }
}
