package com.jorik.taskprovectus.View.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jorik.taskprovectus.BR;
import com.jorik.taskprovectus.R;
import com.jorik.taskprovectus.ViewModel.RandUsersViewModel;
import com.jorik.taskprovectus.databinding.FragmentRandUsersBinding;

public class RandUsersFragment extends BaseFragment<FragmentRandUsersBinding, RandUsersViewModel> {

  public static RandUsersFragment getInstance() {
    RandUsersFragment fragment = new RandUsersFragment();
    Bundle args = new Bundle();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return super.onCreateView(inflater, container, savedInstanceState);
  }

  @Override
  public Toolbar toolbar() {
    return null;
  }

  @Override
  public boolean isHomeButton() {
    return false;
  }

  @Override
  public int getLayoutId() {
    return R.layout.fragment_rand_users;
  }

  @Override
  public int getVariableId() {
    return BR.randUsers;
  }

  @Override
  public RandUsersViewModel getViewModel() {
    return new RandUsersViewModel(getActivity());
  }

  @Override
  public CoordinatorLayout getCoordinatorForSnackbar() {
    return getBinding().coordinatorLayoutFragmentRandUsers;
  }


}
