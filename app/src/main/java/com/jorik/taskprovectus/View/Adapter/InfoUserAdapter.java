package com.jorik.taskprovectus.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.jorik.taskprovectus.BR;
import com.jorik.taskprovectus.Model.Enum.DetailsInfoKind;
import com.jorik.taskprovectus.Model.POJO.InfoUserModel;
import com.jorik.taskprovectus.R;
import com.jorik.taskprovectus.Utils.ResourceUtils;
import com.jorik.taskprovectus.databinding.ItemInfoUserBinding;
import java.util.List;

public class InfoUserAdapter extends BaseAdapter<InfoUserModel, ItemInfoUserBinding> {

  private Context mContext;
  private DetailsInfoKind mInfoKind;
  private ResourceUtils mResourceUtils;

  public InfoUserAdapter(Context context, List<InfoUserModel> listData, DetailsInfoKind infoKind) {
    super(context, listData);
    mContext = context;
    mInfoKind = infoKind;
    mResourceUtils = ResourceUtils.with(context);
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
      Intent actionIntent = null;
      switch (mInfoKind) {
        case PHONE:
          actionIntent = new Intent(Intent.ACTION_CALL);
          actionIntent.setData(Uri.parse(String.format("%s:%s", "tel", inputData)));
          mContext.startActivity(actionIntent);
          break;
        case EMAIL:
          actionIntent = new Intent(Intent.ACTION_SEND);
          actionIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{inputData});
          actionIntent.putExtra(Intent.EXTRA_SUBJECT, mResourceUtils.string(R.string.app_name));
          actionIntent.putExtra(Intent.EXTRA_TEXT, mResourceUtils.string(R.string.info_user_adapter_intent_extra_text));
          actionIntent.setType(mResourceUtils.string(R.string.info_user_adapter_intent_type_text_plain));
          break;
      }
      mContext.startActivity(actionIntent);
    }
  }
}
