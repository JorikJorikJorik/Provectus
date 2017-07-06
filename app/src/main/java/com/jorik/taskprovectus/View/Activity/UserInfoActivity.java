package com.jorik.taskprovectus.View.Activity;

import static com.jorik.taskprovectus.View.Adapter.RandUsersAdapter.USER_INFO_EXTRA;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import com.jorik.taskprovectus.R;
import com.jorik.taskprovectus.View.Fragment.UserInfoFragment;

public class UserInfoActivity extends BaseActivity<UserInfoFragment> {

  private Toolbar mToolbarUserInfo;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mToolbarUserInfo = (Toolbar) findViewById(R.id.toolbarUserInfo);
    setSupportActionBar(mToolbarUserInfo);
    isHomeButton(true);
  }

  @Override
  public int getLayoutId() {
    return R.layout.activity_user_info;
  }

  @Override
  public int getContainerId() {
    return R.id.frameLayoutUserInfo;
  }

  @Override
  public UserInfoFragment getFragment() {
    return UserInfoFragment.getInstance(getIntent().getParcelableExtra(USER_INFO_EXTRA));
  }

}
