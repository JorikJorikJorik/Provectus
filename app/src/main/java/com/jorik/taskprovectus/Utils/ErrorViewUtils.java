package com.jorik.taskprovectus.Utils;

import static com.jorik.taskprovectus.Model.Enum.ErrorViewType.EMPTY;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;
import com.jorik.taskprovectus.Model.Enum.ErrorViewType;
import com.jorik.taskprovectus.Model.POJO.ErrorModel;
import com.jorik.taskprovectus.Model.POJO.ErrorViewModel;
import com.jorik.taskprovectus.R;
import com.jorik.taskprovectus.View.View.ErrorDialog;

public class ErrorViewUtils {

  private final static int UNAUTHORIZED = 401;
  private final static int UNKNOWN_ERROR = -1;
  private final static int LOCAL_CODE_START = 400;
  private final static int LOCAL_CODE_END = 429;
  private final static int NETWORK_CODE_START = 100;
  private final static int NETWORK_CODE_END = 104;
  private final static int SERVER_CODE_START = 500;

  private Context mContext;
  private Runnable actionRefresh;
  private ErrorViewType mErrorViewType;
  private Toast errorToast;
  private Snackbar mSnackbarLoadError;

  public ErrorViewUtils(Context context) {
    mContext = context;
  }

  public ErrorViewUtils(Context context, Runnable action) {
    mContext = context;
    actionRefresh = action;
  }

  public ErrorViewModel formationError(Throwable throwable) {
    ErrorUtils errorUtils = new ErrorUtils(mContext);
    return formationErrorNetwork(errorUtils.parseError(throwable));
  }

  private ErrorViewModel formationErrorNetwork(ErrorModel errorModel) {
    mErrorViewType = parseErrorViewType(errorModel.getCode());
    return formationErrorModel(parseIconResource(), errorModel.getMessage(), mErrorViewType);
  }

  public ErrorViewModel formationErrorEmpty(int iconResource, int messageResource) {
    return formationErrorModel(iconResource, ResourceUtils.with(mContext).string(messageResource), EMPTY);
  }

  public String formationShortError(Throwable throwable) {
    ErrorUtils errorUtils = new ErrorUtils(mContext);
    return errorUtils.chooseMessageByType(errorUtils.parseError(throwable).getErrorNetworkType(), false);
  }

  public void createErrorDialog(int titleResource, Throwable throwable) {
    ErrorUtils errorUtils = new ErrorUtils(mContext);
    createErrorDialogByErrorModel(ResourceUtils.with(mContext).string(titleResource), errorUtils.parseError(throwable));
  }

  public void createErrorDialogByErrorModel(String title, ErrorModel errorModel) {
    AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
    alertDialog.setView(new ErrorDialog(mContext, title, errorModel.getMessage(), alertDialog).createView());
    alertDialog.show();
  }

  public void showErrorToast(Throwable throwable) {
    if (errorToast != null)
      errorToast.cancel();
    errorToast = Toast.makeText(mContext, formationShortError(throwable), Toast.LENGTH_SHORT);
    errorToast.show();
  }

  public void showErrorSnackbar(Throwable throwable, Runnable event, CoordinatorLayout layout) {
    if (layout != null) {
      if (mSnackbarLoadError != null) mSnackbarLoadError.dismiss();
      mSnackbarLoadError = Snackbar.make(layout, formationShortError(throwable), Snackbar.LENGTH_INDEFINITE);
      mSnackbarLoadError.setActionTextColor(ResourceUtils.with(mContext).color(R.color.snackbar_error_action_text));
      mSnackbarLoadError.setAction(R.string.random_users_error_repeat, v -> {
        if (event != null)
          event.run();
      });
      mSnackbarLoadError.show();
    }
  }

  public void dismissErrorSnackbar() {
    if (mSnackbarLoadError != null)
      mSnackbarLoadError.dismiss();
  }

  private ErrorViewModel formationErrorModel(int iconResource, String message, ErrorViewType type) {

    ErrorViewModel errorViewModel = new ErrorViewModel();

    errorViewModel.setErrorType(type);
    errorViewModel.setIconResource(iconResource);
    errorViewModel.setTextError(message);
    errorViewModel.setRefresh(!type.equals(EMPTY));
    errorViewModel.setAction(actionRefresh);

    return errorViewModel;
  }

  private ErrorViewType parseErrorViewType(int errorCode) {
    if (errorCode == UNAUTHORIZED)
      return ErrorViewType.UNAUTHORIZED;
    else if (errorCode == UNKNOWN_ERROR || (errorCode >= LOCAL_CODE_START && errorCode <= LOCAL_CODE_END))
      return ErrorViewType.LOCAL;
    else if (errorCode >= NETWORK_CODE_START && errorCode <= NETWORK_CODE_END)
      return ErrorViewType.NETWORK;
    else if (errorCode >= SERVER_CODE_START)
      return ErrorViewType.SERVER;
    else
      return EMPTY;
  }

  private int parseIconResource() {
    switch (mErrorViewType) {
      case UNAUTHORIZED:
        return R.mipmap.ic_error_authorize;
      case LOCAL:
        return R.mipmap.ic_error_local;
      case NETWORK:
        return R.mipmap.ic_error_network;
      case SERVER:
        return R.mipmap.ic_error_server;
      default:
        return R.mipmap.ic_error_local;
    }
  }

}
