package com.jorik.taskprovectus.Utils.GroupRecyclerUtils;

import android.content.Context;
import com.jorik.taskprovectus.Model.POJO.GroupRecyclerModel;
import com.jorik.taskprovectus.Utils.GroupRecyclerUtils.Groups.BaseGroup;
import com.jorik.taskprovectus.View.Adapter.BaseAdapter;
import com.jorik.taskprovectus.View.Adapter.GroupRecyclerAdapter;
import java.util.ArrayList;
import java.util.List;

public class FormationGroupUtils {

  private Context mContext;
  private List<GroupRecyclerModel> listKindModel;

  public FormationGroupUtils(Context context) {
    mContext = context;
    listKindModel = new ArrayList<>();
  }

  public GroupRecyclerAdapter formationGroupAdapter() {
    return new GroupRecyclerAdapter(mContext, listKindModel);
  }

  public <V extends BaseGroup> void formationGroup(V group) {
    listKindModel.add(formationGroupModel(group.formationSubHeader(), group.formationIcon(), group.formationAdapter()));
  }

  private <T extends BaseAdapter> GroupRecyclerModel formationGroupModel(String subHeader, int iconGroup, T adapter) {
    GroupRecyclerModel<T> groupModel = new GroupRecyclerModel<>();
    groupModel.setIconGroup(iconGroup);
    groupModel.setSubHeader(subHeader);
    groupModel.setAdapterGroup(adapter);
    return groupModel;
  }
}
