package com.jorik.taskprovectus.Model.Enum;


import static android.os.Environment.DIRECTORY_MOVIES;
import static android.os.Environment.DIRECTORY_MUSIC;
import static android.os.Environment.DIRECTORY_PICTURES;

public enum DirectoryKind {

  IMAGE(DIRECTORY_PICTURES),
  MOVIES(DIRECTORY_MOVIES),
  MUSIC(DIRECTORY_MUSIC);

  private String name;

  DirectoryKind(String nameDir) {
    name = nameDir;
  }

  public String getName() {
    return name;
  }
}
