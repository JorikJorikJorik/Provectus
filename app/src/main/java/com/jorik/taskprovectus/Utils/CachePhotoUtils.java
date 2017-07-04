package com.jorik.taskprovectus.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import com.jorik.taskprovectus.Model.Enum.AccessDirectoryKind;

public class CachePhotoUtils {

  private static final String FILE_SUFFIX = "file://";

  private Context mContext;
  private FileUtils mFileUtils;
  private AccessDirectoryKind mAccessDirectoryKind;

  public CachePhotoUtils(Context context, AccessDirectoryKind accessDirectoryKind) {
    mContext = context;
    mFileUtils = new FileUtils(mContext);
    mAccessDirectoryKind = accessDirectoryKind;
  }


  public void loadBitmap(Bitmap bitmap, String url) {
    mFileUtils.loadPhotoByBitmap(bitmap, url, mAccessDirectoryKind);
  }

  public Bitmap uploadBitmap(String url) {
    return mFileUtils.uploadBitmapFromPhoto(url, mAccessDirectoryKind);
  }

  public String uploadPath(String url) {
    return String.format("%s%s", FILE_SUFFIX, mFileUtils.generatePhotoFile(url, mAccessDirectoryKind).getPath());
  }

}
