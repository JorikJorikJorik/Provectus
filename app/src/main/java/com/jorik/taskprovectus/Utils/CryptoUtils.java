package com.jorik.taskprovectus.Utils;

import android.content.Context;
import android.util.Base64;
import com.jorik.taskprovectus.R;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CryptoUtils {

  private static final String TYPE_CRYPTO_ALGORITHM = "AES/CBC/PKCS5PADDING";
  private static final String UTF_8 = "UTF-8";
  private static final String ENCRYPT_KEY = "cash_encrypt_key";
  private static final String IV_KEY = "cash_encrypt_iv_";

  private Context mContext;
  private ResourceUtils mResourceUtils;
  private SecretKeySpec mSecretKeySpec;
  private IvParameterSpec mIvParameterSpec;

  public CryptoUtils(Context context) {
    mContext = context;
    mResourceUtils = ResourceUtils.with(mContext);
  }

  public String getData(String inputData, int mode) {
    String result = mResourceUtils.string(R.string.empty_string);
    try {
      byte[] cipherData = cipherData(inputData.getBytes(UTF_8), mode);
      result = new String(cipherData, UTF_8);
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return result;
  }

  private void generateKey() {
    mSecretKeySpec = new SecretKeySpec(ENCRYPT_KEY.getBytes(), TYPE_CRYPTO_ALGORITHM);
    mIvParameterSpec = new IvParameterSpec(IV_KEY.getBytes());
  }

  private byte[] cipherData(byte[] data, int mode) {
    if (mSecretKeySpec != null && mIvParameterSpec != null) {
      try {
        Cipher cipher = Cipher.getInstance(TYPE_CRYPTO_ALGORITHM);
        cipher.init(mode, mSecretKeySpec, mIvParameterSpec);
        return cipher.doFinal(data);
      } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException | InvalidAlgorithmParameterException e) {
        e.printStackTrace();
      }
    }
    return new byte[]{};
  }

  public String generateBasicForAuthorization(String login, String password) {
    return String.format("%s %s", "Basic", generateBasic(String.format("%s:%s", login, password)));
  }

  public String generateBasic(String inputData) {
    try {
      return Base64.encodeToString(inputData.getBytes(UTF_8), Base64.NO_WRAP);
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return mResourceUtils.string(R.string.empty_string);
  }

}
