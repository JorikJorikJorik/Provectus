package com.jorik.taskprovectus.View.View;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.jorik.taskprovectus.R;

public class PermissionDialog extends BaseView {

  private int warnMessageId;
  private int positiveMessageId;
  private Runnable event;
  private AlertDialog mDialog;

  private TextView mTextViewPermissionTitle;
  private TextView mTextViewPermissionMessage;
  private Button mButtonPermissionCancel;
  private Button mButtonPermissionNextStep;

  public PermissionDialog(Context context, int warnMessageId, int positiveMessageId, Runnable event, AlertDialog dialog) {
    super(context);
    this.warnMessageId = warnMessageId;
    this.positiveMessageId = positiveMessageId;
    this.event = event;
    mDialog = dialog;
  }

  @Override
  public int getLayoutId() {
    return R.layout.dialog_permission;
  }

  @Override
  public View createView() {
    View dialogView = super.createView();

    mTextViewPermissionTitle = (TextView) dialogView.findViewById(R.id.textViewPermissionTitle);
    mTextViewPermissionMessage = (TextView) dialogView.findViewById(R.id.textViewPermissionMessage);
    mButtonPermissionCancel = (Button) dialogView.findViewById(R.id.buttonPermissionCancel);
    mButtonPermissionNextStep = (Button) dialogView.findViewById(R.id.buttonPermissionNextStep);

    mTextViewPermissionTitle.setText(R.string.permission_utils_dialog_title);
    mTextViewPermissionMessage.setText(warnMessageId);
    mButtonPermissionCancel.setOnClickListener(v -> mDialog.cancel());

    mButtonPermissionNextStep.setText(positiveMessageId);
    mButtonPermissionNextStep.setOnClickListener(v -> {
      if (event != null)
        event.run();
      mDialog.cancel();
    });

    return dialogView;
  }
}
