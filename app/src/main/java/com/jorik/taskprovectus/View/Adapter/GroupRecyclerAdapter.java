package com.jorik.taskprovectus.View.Adapter;

import android.content.Context;
import com.jorik.taskprovectus.BR;
import com.jorik.taskprovectus.Model.POJO.GroupRecyclerModel;
import com.jorik.taskprovectus.R;
import com.jorik.taskprovectus.databinding.ItemGroupRecyclerBinding;
import java.util.List;

public class GroupRecyclerAdapter extends BaseAdapter<GroupRecyclerModel, ItemGroupRecyclerBinding> {

  public GroupRecyclerAdapter(Context context, List<GroupRecyclerModel> listData) {
    super(context, listData);
  }

  @Override
  public int getLayoutId() {
    return R.layout.item_group_recycler;
  }

  @Override
  public int getVariableId() {
    return BR.group;
  }

  @Override
  public int getAdapterVariableId() {
    return 0;
  }
}
