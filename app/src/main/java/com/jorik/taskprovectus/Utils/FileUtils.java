package com.jorik.taskprovectus.Utils;

import static com.jorik.taskprovectus.Model.Enum.DirectoryKind.IMAGE;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.os.Environment;
import com.jorik.taskprovectus.Model.Enum.AccessDirectoryKind;
import com.jorik.taskprovectus.Model.Enum.DirectoryKind;
import com.jorik.taskprovectus.R;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class FileUtils {

  private static final String CACHE_RESPONSE_FILE = "cache.txt";
  private static final String PHOTO_TYPE = "png";
  private static final int QUALITY_PHOTO = 99;

  private Context mContext;
  private CryptoUtils mCryptoUtils;
  private ResourceUtils mResourceUtils;

  public FileUtils(Context context) {
    mContext = context;
    mCryptoUtils = new CryptoUtils(mContext);
    mResourceUtils = ResourceUtils.with(mContext);
  }

  public void writeToFile(String data) {
    if (!isEmptyPathCacheFile()) {
      try {
        FileOutputStream outputStream = new FileOutputStream(accessToHideCacheFile());
        outputStream.write(data.getBytes());
        outputStream.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public String readFromFile() {
    String result = mResourceUtils.string(R.string.empty_string);
    if (!isEmptyPathCacheFile()) {
      try {
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        FileReader fileReader = new FileReader(accessToHideCacheFile());
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        while ((line = bufferedReader.readLine()) != null) {
          stringBuilder.append(line);
        }

        fileReader.close();
        bufferedReader.close();

        result = stringBuilder.toString();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return result;
  }

  public void deleteFile(String path, String nameFile) {
    File deleteFile = new File(path, nameFile);
    if (deleteFile.exists())
      deleteFile.delete();
  }

  public String loadPhotoByBitmap(Bitmap photoBitmap, String photoUrl, AccessDirectoryKind accessKind) {
    File photoFile = null;
    try {
      photoFile = generatePhotoFile(photoUrl, accessKind);
      FileOutputStream fileOutputStream = new FileOutputStream(photoFile);
      photoBitmap.compress(CompressFormat.PNG, QUALITY_PHOTO, fileOutputStream);
      fileOutputStream.flush();
      fileOutputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return photoFile.getPath();
  }

  public Bitmap uploadBitmapFromPhoto(String photoUrl, AccessDirectoryKind accessKind) {
    Bitmap photoBitmap = null;
    File photoFile = generatePhotoFile(photoUrl, accessKind);
    if (photoFile.exists()) {
      BitmapFactory.Options options = new BitmapFactory.Options();
      options.inPreferredConfig = Config.ARGB_8888;
      photoBitmap = BitmapFactory.decodeFile(photoFile.getPath(), options);
    }
    return photoBitmap;
  }

  public File generatePhotoFile(String photoUrl, AccessDirectoryKind accessKind) {
    return new File(pathDirByAccessKind(accessKind, IMAGE), String.format("%s.%s", mCryptoUtils.generateBasic(photoUrl), PHOTO_TYPE));
  }

  private String pathDirByAccessKind(AccessDirectoryKind accessKind, DirectoryKind dirKind) {
    switch (accessKind) {
      case HIDE:
        return accessToHideFileDirectory(dirKind);
      case KIND:
        return accessToKindDirectory(dirKind);
      case PUBLIC_KIND:
        return accessToPublicKindDirectory(dirKind);
      default:
        return mResourceUtils.string(R.string.file_utils_no_file);
    }
  }

  private boolean isEmptyPathCacheFile() {
    return accessToHideCacheFile().isEmpty();
  }

  private String accessToHideCacheFile() {
    File cashFile = null;
    File cacheDir = mContext.getExternalCacheDir();
    if (cacheDir != null) {
      cashFile = new File(cacheDir.getPath(), CACHE_RESPONSE_FILE);
      if (!cashFile.exists()) {
        try {
          cashFile.createNewFile();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return cashFile != null ? cashFile.getPath() : mResourceUtils.string(R.string.empty_string);
  }

  private String accessToHideFileDirectory(DirectoryKind kind) {
    File fileDir = mContext.getExternalFilesDir(kind.getName());
    return fileDir != null ? fileDir.getPath() : mResourceUtils.string(R.string.empty_string);
  }

  private String accessToKindDirectory(DirectoryKind kind) {
    String parentDir = getPathDir(Environment.getExternalStorageDirectory(), mResourceUtils.string(R.string.app_name));
    return getPathDir(parentDir, String.format("%s %s", mResourceUtils.string(R.string.app_name), kind.name().toLowerCase()));
  }

  private String accessToPublicKindDirectory(DirectoryKind kind) {
    return getPathDir(Environment.getExternalStoragePublicDirectory(kind.getName()), mResourceUtils.string(R.string.app_name));
  }

  private String getPathDir(String parent, String name) {
    return getPathDirFromFile(new File(parent, name));
  }

  private String getPathDir(File parent, String name) {
    return getPathDirFromFile(new File(parent, name));
  }

  private String getPathDirFromFile(File directory) {
    if (!directory.exists()) {
      if (!directory.mkdir())
        return mResourceUtils.string(R.string.file_utils_no_file);
    }
    return directory.getPath();
  }
}
