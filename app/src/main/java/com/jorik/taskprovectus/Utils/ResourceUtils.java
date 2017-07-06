package com.jorik.taskprovectus.Utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.TypedValue;

public class ResourceUtils {
  private static final int DEFAULT_VALUE = 0;

  private static ResourceUtils sResourceUtils;
  private Context mContext;

  public ResourceUtils(Context context) {
    mContext = context;
  }

  public static ResourceUtils with(Context context) {
    if (sResourceUtils == null) {
      sResourceUtils = new ResourceUtils(context);
    }
    return sResourceUtils;
  }

  public String string(int resourceId) {
    return mContext.getResources().getString(resourceId);
  }

  public float dimens(int resourceId) {
    return mContext.getResources().getDimension(resourceId);
  }

  public int color(int resourceId) {
    return ContextCompat.getColor(mContext, resourceId);
  }

  public Drawable drawable(int resourceId){
    return VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP ? mContext.getDrawable(resourceId) : mContext.getResources().getDrawable(resourceId);
  }

  public String[] stringArray(int resourceId) {
    return mContext.getResources().getStringArray(resourceId);
  }

  public Integer[] integerArray(int resourceId) {
    TypedArray typedArray = mContext.getResources().obtainTypedArray(resourceId);

    Integer[] resourceArray = new Integer[typedArray.length()];
    for (int i = 0; i < resourceArray.length; i++) {
      resourceArray[i] = typedArray.getResourceId(i, DEFAULT_VALUE);
    }
    typedArray.recycle();
    return resourceArray;
  }

  public int convertDpToPx(int dp) {
    DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
    return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
  }

  public int convertPxToDp(int px) {
    DisplayMetrics displaymetrics = new DisplayMetrics();
    return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, displaymetrics);
  }


}
