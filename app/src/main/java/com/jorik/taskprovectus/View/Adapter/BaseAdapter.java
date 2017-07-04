package com.jorik.taskprovectus.View.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jorik.taskprovectus.View.Adapter.BaseAdapter.ViewHolder;
import java.util.List;

public abstract class BaseAdapter<T, B extends ViewDataBinding> extends RecyclerView.Adapter<ViewHolder> {

  private Context mContext;
  private List<T> listData;

  public BaseAdapter(Context context, List<T> listData) {
    mContext = context;
    this.listData = listData;
  }

  public abstract int getLayoutId();

  public abstract int getVariableId();

  public abstract int getAdapterVariableId();

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(mContext);
    B binding = DataBindingUtil.inflate(inflater, getLayoutId(), parent, false);
    return new ViewHolder(binding.getRoot());
  }

  @Override
  public void onBindViewHolder(BaseAdapter.ViewHolder holder, int position) {
    T item = listData.get(position);
    if (item != null) {
      B binding = (B) holder.getBinding();
      binding.setVariable(getVariableId(), item);
      int variableAdapter = getAdapterVariableId();
      if (variableAdapter != 0) {
        binding.setVariable(variableAdapter, this);
      }
      binding.executePendingBindings();
    }
  }

  @Override
  public int getItemCount() {
    return listData.size();
  }

  public List<T> getListData() {
    return listData;
  }

  public void setListData(List<T> listData) {
    this.listData = listData;
  }

  class ViewHolder extends RecyclerView.ViewHolder {

    private B binding;

    ViewHolder(View itemView) {
      super(itemView);
      binding = DataBindingUtil.bind(itemView);
    }

    B getBinding() {
      return binding;
    }
  }
}
