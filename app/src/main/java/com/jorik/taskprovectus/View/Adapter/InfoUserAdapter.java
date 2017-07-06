package com.jorik.taskprovectus.View.Adapter;

import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.SEND_SMS;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import com.jorik.taskprovectus.BR;
import com.jorik.taskprovectus.DataBase.DataBaseUtils;
import com.jorik.taskprovectus.Model.Enum.DetailsInfoKind;
import com.jorik.taskprovectus.Model.POJO.InfoUserModel;
import com.jorik.taskprovectus.R;
import com.jorik.taskprovectus.Utils.IntentUtils;
import com.jorik.taskprovectus.Utils.PermissionUtils;
import com.jorik.taskprovectus.databinding.ItemInfoUserBinding;
import java.util.List;

public class InfoUserAdapter extends BaseAdapter<InfoUserModel, ItemInfoUserBinding> {

  private Context mContext;
  private DetailsInfoKind mInfoKind;
  private IntentUtils mIntentUtils;
  private DataBaseUtils mDataBaseUtils;
  private PermissionUtils mPermissionUtils;

  public InfoUserAdapter(Context context, List<InfoUserModel> listData, DetailsInfoKind infoKind) {
    super(context, listData);
    mContext = context;
    mInfoKind = infoKind;
    mIntentUtils = new IntentUtils(context);
    mDataBaseUtils = new DataBaseUtils();
    mPermissionUtils = new PermissionUtils(context, mDataBaseUtils);
  }

  @Override
  public int getLayoutId() {
    return R.layout.item_info_user;
  }

  @Override
  public int getVariableId() {
    return BR.infoUser;
  }

  @Override
  public int getAdapterVariableId() {
    return 0;
  }

  @Override
  public void onBindViewHolder(BaseAdapter.ViewHolder holder, int position) {
    super.onBindViewHolder(holder, position);
    ItemInfoUserBinding binding = (ItemInfoUserBinding) holder.getBinding();
    binding.imageViewActionIcon.setOnClickListener(v -> chooseActionByKind(position));
  }

  private void chooseActionByKind(int position) {
    String inputData = getListData().get(position).getMainData();
    if (inputData != null) {
      switch (mInfoKind) {
        case PHONE:
          if (mPermissionUtils.checkPermission(CALL_PHONE))
            mIntentUtils.callNumber(inputData);
          break;
        case EMAIL:
          if (mPermissionUtils.checkPermission(SEND_SMS))
            mIntentUtils.sendEmail(inputData);
          break;
      }
    }
  }

  @Override
  public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
    mDataBaseUtils.close();
    super.onDetachedFromRecyclerView(recyclerView);
  }
}
