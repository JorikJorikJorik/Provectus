package com.jorik.taskprovectus.View.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

abstract class BaseView
{

  private Context mContext;

  BaseView(Context context) {
    mContext = context;
  }

  public abstract int getLayoutId();

  public View createView() {
    return LayoutInflater.from(mContext).inflate(getLayoutId(), null);
  }
}
