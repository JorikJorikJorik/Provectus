package com.jorik.taskprovectus.Utils;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static com.jorik.taskprovectus.Model.Enum.PermissionState.CONFIRM;
import static com.jorik.taskprovectus.Model.Enum.PermissionState.DENIED;
import static com.jorik.taskprovectus.Model.Enum.PermissionState.DONT_ASK;
import static com.jorik.taskprovectus.Model.Enum.PermissionState.FIRST;
import static com.jorik.taskprovectus.R.string;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import com.jorik.taskprovectus.DataBase.DataBaseUtils;
import com.jorik.taskprovectus.DataBase.Tables.PermissionTable;
import com.jorik.taskprovectus.DataBase.TablesUtils.PermissionTableUtils;
import com.jorik.taskprovectus.Model.Enum.PermissionState;
import com.jorik.taskprovectus.View.Activity.BaseActivity;
import com.jorik.taskprovectus.View.View.PermissionDialog;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PermissionUtils {

  private static final int PERMISSION_REQUEST = 5;

  private Context mContext;
  private ArrayList<String> permissionList;
  private ResourceUtils resourceUtils;
  private PermissionTableUtils mPermissionTableUtils;
  private AlertDialog explainPermissionDialog;

  public PermissionUtils(Context context, DataBaseUtils dataBaseUtils) {
    mContext = context;
    resourceUtils = ResourceUtils.with(mContext);
    mPermissionTableUtils = new PermissionTableUtils(dataBaseUtils);
  }

  public PermissionUtils(Context context, ArrayList<String> permissions, DataBaseUtils dataBaseUtils) {
    mContext = context;
    permissionList = permissions;
    resourceUtils = ResourceUtils.with(mContext);
    mPermissionTableUtils = new PermissionTableUtils(dataBaseUtils);
  }

  public boolean checkPermission(String permission) {
    if (VERSION.SDK_INT >= VERSION_CODES.M) {
      boolean isGranted = isGrantedPermission(permission);
      if (!isGranted) {
        permissionList = new ArrayList<>(Collections.singletonList(permission));
        workUtils();
      }
      return isGranted;
    }
    return true;
  }

  public void workUtils() {
    if (VERSION.SDK_INT >= VERSION_CODES.M) {
      checkDataBaseChangePermission();
      checkSettingsChangePermission();
      requestGetPermission();
    }
  }

  private void checkDataBaseChangePermission() {
    List<PermissionTable> permissionTable = mPermissionTableUtils.getTableValues();
    if (permissionTable == null) {
      for (String permission : permissionList)
        mPermissionTableUtils.createValue(permission);
    } else {
      for (PermissionTable itemTable : permissionTable) {
        String namePermission = itemTable.getNamePermission();
        if (!permissionList.contains(namePermission) && !mPermissionTableUtils.existValue(namePermission))
          mPermissionTableUtils.createValue(namePermission);
      }
    }
  }

  private void checkSettingsChangePermission() {
    for (PermissionTable item : mPermissionTableUtils.getTableValues()) {
      boolean stateCheckDataBase = item.getPermissionStateValue() == CONFIRM.getValue();
      if (isGrantedPermission(item.getNamePermission()) != stateCheckDataBase)
        mPermissionTableUtils.updatePermissionValue(item.getNamePermission(), stateCheckDataBase ? FIRST : CONFIRM);
    }
  }

  private void requestGetPermission() {
    if (mPermissionTableUtils.getTableValues().size() > 0) {
      List<String> permissionsFirst = parsePermissionByState(FIRST);
      if (!permissionsFirst.isEmpty())
        showRequestPermissionDialog(permissionsFirst);
      else
        sequenceCallDialog();
    }
  }

  private List<String> parsePermissionByState(PermissionState state) {
    List<String> permissions = new ArrayList<>();
    for (PermissionTable value : mPermissionTableUtils.getTableValues()) {
      if (value.getPermissionStateValue() == state.getValue())
        permissions.add(value.getNamePermission());
    }
    return permissions;
  }

  private void sequenceCallDialog() {
    List<String> permissionsDenied = parsePermissionByState(DENIED);
    if (!permissionsDenied.isEmpty())
      explainPermissionDialog(string.permission_utils_dialog_message_repeat, string.permission_utils_dialog_action_repeat, () -> showRequestPermissionDialog(permissionsDenied));
    else if (!parsePermissionByState(DONT_ASK).isEmpty())
      explainPermissionDialog(string.permission_utils_dialog_message_general, string.permission_utils_dialog_action_settings, this::moveToSettings);
  }

  public void handleRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
    if (requestCode == PERMISSION_REQUEST && grantResults.length > 0) {
      for (int i = 0; i < grantResults.length; i++) {
        String permission = permissions[i];
        mPermissionTableUtils.updatePermissionValue(permission, grantResults[i] == PERMISSION_GRANTED ? CONFIRM : isShowRequestPermission(permission) ? DENIED : DONT_ASK);
      }
      sequenceCallDialog();
    }
  }

  private void explainPermissionDialog(int warnMessageId, int positiveMessageId, Runnable event) {
    if (explainPermissionDialog != null)
      explainPermissionDialog.dismiss();

    explainPermissionDialog = new AlertDialog
      .Builder(mContext)
      .setCancelable(false)
      .create();

    explainPermissionDialog.setView(new PermissionDialog(mContext, warnMessageId, positiveMessageId, event, explainPermissionDialog).createView());
    explainPermissionDialog.show();
  }

  private void showRequestPermissionDialog(List<String> permissionList) {
    ActivityCompat.requestPermissions((BaseActivity) mContext, permissionList.toArray(new String[permissionList.size()]), PERMISSION_REQUEST);
  }

  private boolean isShowRequestPermission(String permission) {
    return ActivityCompat.shouldShowRequestPermissionRationale((BaseActivity) mContext, permission);
  }

  private boolean isGrantedPermission(String permission) {
    return ContextCompat.checkSelfPermission(mContext, permission) == PERMISSION_GRANTED;
  }

  private void moveToSettings() {
    Intent intentSettings = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts(resourceUtils.string(string.permission_utils_move_settings_package), mContext.getPackageName(), null));
    intentSettings.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    mContext.startActivity(intentSettings);
  }
}
