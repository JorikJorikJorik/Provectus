package com.jorik.taskprovectus.View.Fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jorik.taskprovectus.View.Activity.BaseActivity;
import com.jorik.taskprovectus.View.Fragment.Callback.CoordinatorForSnackbarCallback;
import com.jorik.taskprovectus.View.Fragment.Callback.UpdateToolbarCallback;
import com.jorik.taskprovectus.ViewModel.BaseViewModel;

public abstract class BaseFragment<B extends ViewDataBinding, V extends BaseViewModel> extends Fragment implements UpdateToolbarCallback, CoordinatorForSnackbarCallback {

  private B binding;
  private V viewModel;

  public abstract Toolbar toolbar();

  public abstract boolean isHomeButton();

  public abstract int getLayoutId();

  public abstract int getVariableId();

  public abstract V getViewModel();

  public abstract CoordinatorLayout getCoordinatorForSnackbar();

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(getLayoutId(), container, false);
    binding = DataBindingUtil.bind(view);
    createToolbar();
    viewModel = getViewModel();
    registerCallback();
    binding.setVariable(getVariableId(), viewModel);
    binding.executePendingBindings();
    viewModel.onCreateView();
    return view;
  }

  @Override
  public void onStart() {
    super.onStart();
    viewModel.onStart();
  }

  @Override
  public void onResume() {
    super.onResume();
    viewModel.onResume();
  }

  @Override
  public void onPause() {
    viewModel.onPause();
    super.onPause();
  }

  @Override
  public void onStop() {
    viewModel.onStop();
    super.onStop();
  }

  @Override
  public void onDestroy() {
    viewModel.onDestroy();
    super.onDestroy();
  }

  public B getBinding() {
    return binding;
  }

  private void registerCallback() {
    viewModel.registerCallbackFinish((BaseActivity) getActivity());
    viewModel.registerCallbackToolbarUpdate(this);
    viewModel.registerCallbackCoordinatorForSnackbar(this);
  }

  @Override
  public void updateToolbar(String message) {
    ActionBar actionBar = ((BaseActivity) getActivity()).getSupportActionBar();
    if (actionBar != null)
      actionBar.setTitle(message);
  }

  public void requestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
    viewModel.requestPermissionsResult(requestCode, permissions, grantResults);
  }

  private void createToolbar() {
    ActionBar actionBar = ((BaseActivity) getActivity()).getSupportActionBar();
    if (actionBar == null && toolbar() != null) {
      ((BaseActivity) getActivity()).setSupportActionBar(toolbar());
      ((BaseActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(isHomeButton());
    }
  }

  @Override
  public CoordinatorLayout coordinatorForSnackbar() {
    return getCoordinatorForSnackbar();
  }
}
