package com.jorik.taskprovectus.View.View;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.jorik.taskprovectus.R;

public class ErrorDialog extends BaseView {

  private String title;
  private String message;
  private AlertDialog mDialog;

  private TextView mTextViewErrorTitle;
  private TextView mTextViewErrorMessage;
  private Button mButtonErrorOk;

  public ErrorDialog(Context context, String title, String message, AlertDialog dialog) {
    super(context);
    this.title = title;
    this.message = message;
    mDialog = dialog;
  }

  @Override
  public int getLayoutId() {
    return R.layout.dialog_error;
  }

  @Override
  public View createView() {
    View errorDialog = super.createView();

    mTextViewErrorTitle = (TextView) errorDialog.findViewById(R.id.textViewErrorTitle);
    mTextViewErrorMessage = (TextView) errorDialog.findViewById(R.id.textViewErrorMessage);
    mButtonErrorOk = (Button) errorDialog.findViewById(R.id.buttonErrorOk);

    mTextViewErrorTitle.setText(message);
    mTextViewErrorMessage.setText(title);
    mButtonErrorOk.setOnClickListener(v -> mDialog.cancel());

    return errorDialog;
  }
}
