package com.jorik.taskprovectus.View.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import com.jorik.taskprovectus.View.Activity.Callback.FinishActivityCallback;
import com.jorik.taskprovectus.View.Fragment.BaseFragment;

public abstract class BaseActivity<F extends BaseFragment> extends AppCompatActivity implements FinishActivityCallback {

  private F fragment;

  public abstract int getLayoutId();

  public abstract int getContainerId();

  public abstract F getFragment();


  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getLayoutId());

    fragment = getFragment();

    FragmentManager fragmentManager = getSupportFragmentManager();
    fragmentManager
      .beginTransaction()
      .replace(getContainerId(), fragment)
      .commit();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        finish();
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  public void isHomeButton(boolean needed){
    if (getSupportActionBar() != null) {
      getSupportActionBar().setDisplayHomeAsUpEnabled(needed);
    }
  }

  @Override
  public void finishActivity() {
    finish();
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    fragment.requestPermissionsResult(requestCode, permissions, grantResults);
  }
}
