package com.jorik.taskprovectus.Utils.GroupRecyclerUtils.Groups;

import android.content.Context;
import com.jorik.taskprovectus.View.Adapter.BaseAdapter;

public abstract class BaseGroup<V, T extends BaseAdapter> {

  private Context mContext;
  private V data;

  public BaseGroup(Context context, V data) {
    mContext = context;
    this.data = data;
  }

  public abstract String formationSubHeader();

  public abstract Integer formationIcon();

  public abstract T formationAdapter();

  public Context getContext() {
    return mContext;
  }

  public V getData() {
    return data;
  }
}
