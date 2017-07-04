package com.jorik.taskprovectus.ViewModel;

import android.support.design.widget.CoordinatorLayout;
import com.jorik.taskprovectus.DataBase.DataBaseUtils;
import com.jorik.taskprovectus.View.Activity.Callback.FinishActivityCallback;
import com.jorik.taskprovectus.View.Fragment.Callback.CoordinatorForSnackbarCallback;
import com.jorik.taskprovectus.View.Fragment.Callback.UpdateToolbarCallback;
import rx.subscriptions.CompositeSubscription;

public class BaseViewModel {

  private CompositeSubscription mSubscription;
  private DataBaseUtils mDataBaseUtils;

  private FinishActivityCallback mFinishActivityCallback;
  private UpdateToolbarCallback mUpdateToolbarCallback;
  private CoordinatorForSnackbarCallback mCoordinatorForSnackbarCallback;

  public void onCreateView() {

  }

  public void onStart() {

  }

  public void onResume() {

  }

  public void onPause() {

  }

  public void onStop() {

  }

  public void onDestroy() {
    if (mSubscription != null)
      mSubscription.unsubscribe();
    if (mDataBaseUtils != null)
      mDataBaseUtils.close();
  }

  public void initSubscription(CompositeSubscription subscription) {
    mSubscription = subscription;
  }

  public void initDataBase(DataBaseUtils dataBaseUtils) {
    mDataBaseUtils = dataBaseUtils;
  }

  public void finish() {
    if (mFinishActivityCallback != null)
      mFinishActivityCallback.finishActivity();
  }

  public void requestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

  }

  public void updateTitleToolbar(String title) {
    if (mUpdateToolbarCallback != null)
      mUpdateToolbarCallback.updateToolbar(title);
  }

  public CoordinatorLayout getCoordinatorForSnackbar() {
    CoordinatorLayout layout = null;
    if (mCoordinatorForSnackbarCallback != null)
      layout = mCoordinatorForSnackbarCallback.coordinatorForSnackbar();
    return layout;
  }

  public void registerCallbackFinish(FinishActivityCallback callback) {
    mFinishActivityCallback = callback;
  }

  public void registerCallbackToolbarUpdate(UpdateToolbarCallback callback) {
    mUpdateToolbarCallback = callback;
  }

  public void registerCallbackCoordinatorForSnackbar(CoordinatorForSnackbarCallback callback) {
    mCoordinatorForSnackbarCallback = callback;
  }
}
