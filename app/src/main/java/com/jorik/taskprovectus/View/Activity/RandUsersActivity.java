package com.jorik.taskprovectus.View.Activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import com.jorik.taskprovectus.R;
import com.jorik.taskprovectus.View.Fragment.RandUsersFragment;

public class RandUsersActivity extends BaseActivity<RandUsersFragment> {

  private Toolbar toolbarRandUsers;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    toolbarRandUsers = (Toolbar) findViewById(R.id.toolbarRandUsers);
    toolbarRandUsers.setTitle(R.string.random_users_toolbar_title);
    setSupportActionBar(toolbarRandUsers);
  }

  @Override
  public int getLayoutId() {
    return R.layout.activity_rand_users;
  }

  @Override
  public int getContainerId() {
    return R.id.frameLayoutRandUsers;
  }

  @Override
  public RandUsersFragment getFragment() {
    return RandUsersFragment.getInstance();
  }

  @Override
  public boolean isHomeButton() {
    return false;
  }
}
