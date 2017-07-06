package com.jorik.taskprovectus.Utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.jorik.taskprovectus.R;

public class IntentUtils {

  private Context mContext;
  private ResourceUtils mResourceUtils;

  public IntentUtils(Context context) {
    mContext = context;
    mResourceUtils = ResourceUtils.with(context);
  }

  public void callNumber(String inputData) {
    Intent callIntent = new Intent(Intent.ACTION_CALL);
    callIntent.setData(Uri.parse(String.format("%s:%s", "tel", inputData)));
    mContext.startActivity(callIntent);
  }

  public void sendEmail(String inputData) {
    Intent emailIntent = new Intent(Intent.ACTION_SEND);
    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{inputData});
    emailIntent.putExtra(Intent.EXTRA_SUBJECT, mResourceUtils.string(R.string.app_name));
    emailIntent.putExtra(Intent.EXTRA_TEXT, mResourceUtils.string(R.string.intent_utils_send_email_extra_text));
    emailIntent.setType(mResourceUtils.string(R.string.intent_utils_send_email_type_text_plain));
    mContext.startActivity(Intent.createChooser(emailIntent, mResourceUtils.string(R.string.intent_utils_send_email_progress)));
  }

}
