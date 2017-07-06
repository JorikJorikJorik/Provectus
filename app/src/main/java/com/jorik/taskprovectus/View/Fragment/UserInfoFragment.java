package com.jorik.taskprovectus.View.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jorik.taskprovectus.BR;
import com.jorik.taskprovectus.Network.DTO.UserDataDTO;
import com.jorik.taskprovectus.R;
import com.jorik.taskprovectus.ViewModel.UserInfoViewModel;
import com.jorik.taskprovectus.databinding.FragmentUserInfoBinding;

public class UserInfoFragment extends BaseFragment<FragmentUserInfoBinding, UserInfoViewModel> {

  private static final String USER_DATA_ARGS = "user_data_args";

  private UserDataDTO mUserDataDTO;

  public static UserInfoFragment getInstance(UserDataDTO userDataDTO) {
    UserInfoFragment fragment = new UserInfoFragment();
    Bundle args = new Bundle();
    args.putParcelable(USER_DATA_ARGS, userDataDTO);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
    mUserDataDTO = getArguments().getParcelable(USER_DATA_ARGS);
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return super.onCreateView(inflater, container, savedInstanceState);
  }

  @Override
  public Toolbar toolbar() {
    return getBinding().toolbarInfoUser;
  }

  @Override
  public boolean isHomeButton() {
    return true;
  }

  @Override
  public int getLayoutId() {
    return R.layout.fragment_user_info;
  }

  @Override
  public int getVariableId() {
    return BR.userInfo;
  }

  @Override
  public UserInfoViewModel getViewModel() {
    return new UserInfoViewModel(getActivity(), mUserDataDTO);
  }

  @Override
  public CoordinatorLayout getCoordinatorForSnackbar() {
    return null;
  }
}
