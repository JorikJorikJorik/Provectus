package com.jorik.taskprovectus.View.Adapter;

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.content.Context;
import android.view.View;
import com.jorik.taskprovectus.BR;
import com.jorik.taskprovectus.Network.DTO.UserDataDTO;
import com.jorik.taskprovectus.R;
import com.jorik.taskprovectus.View.Activity.BaseActivity;
import com.jorik.taskprovectus.View.Fragment.UserShortInfoBottomSheet;
import com.jorik.taskprovectus.databinding.ItemUserBinding;
import java.util.List;

public class RandUsersAdapter extends BaseAdapter<UserDataDTO, ItemUserBinding> {

  private Context mContext;
  private ItemUserBinding binding;

  public RandUsersAdapter(Context context, List<UserDataDTO> listData) {
    super(context, listData);
    mContext = context;
  }

  @Override
  public int getLayoutId() {
    return R.layout.item_user;
  }

  @Override
  public int getVariableId() {
    return BR.user;
  }

  @Override
  public int getAdapterVariableId() {
    return 0;
  }

  @Override
  public void onBindViewHolder(BaseAdapter.ViewHolder holder, int position) {
    super.onBindViewHolder(holder, position);
    binding = (ItemUserBinding) holder.getBinding();

    boolean stateInfo = getListData().get(position) != null;
    stateItem(binding.constraintLayoutInfoBox, stateInfo);
    stateItem(binding.constraintLayoutProgressBox, !stateInfo);

    binding.constraintLayoutItemUser.setOnLongClickListener(v -> {
      UserDataDTO checkItem = getListData().get(position);
      if (checkItem != null) {
        UserShortInfoBottomSheet bottomSheet = UserShortInfoBottomSheet.getInstance(checkItem);
        bottomSheet.show(((BaseActivity) mContext).getSupportFragmentManager(), bottomSheet.getTag());
      }
      return true;
    });
  }

  private void stateItem(View view, boolean stateInfo) {
    view.setVisibility(stateInfo ? VISIBLE : GONE);
  }

  public void hideProgressLoading(boolean isLoading) {
    binding.progressBarLoading.setVisibility(isLoading ? VISIBLE : INVISIBLE);
  }
}
