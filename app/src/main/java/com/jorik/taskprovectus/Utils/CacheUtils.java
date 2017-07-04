package com.jorik.taskprovectus.Utils;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.HashMap;
import java.util.Map;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CacheUtils<T> {

  private Context mContext;
  private CryptoUtils mCryptoUtils;
  private FileUtils mFileUtils;
  private Map<String, String> currentCacheMap;
  private Gson gson;

  public CacheUtils(Context context) {
    mContext = context;
    mCryptoUtils = new CryptoUtils(mContext);
    mFileUtils = new FileUtils(mContext);
    currentCacheMap = new HashMap<>();
    gson = new Gson();
  }

  public Observable<T> asyncWrite(T data) {
    return Observable
      .just(data)
      .observeOn(Schedulers.io())
      .doOnNext(this::write)
      .observeOn(AndroidSchedulers.mainThread());
  }

  private void write(T data) {
    String key = data.getClass().toString();
    String dataJson = gson.toJson(data);

    currentCacheMap = getCurrentCacheMap();
    currentCacheMap.put(key, dataJson);
    saveCurrentMap();
  }

  public Observable<T> asyncRead(Class<T> classData) {
    return Observable
      .just(read(classData))
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread());
  }

  private T read(Class<T> classData) {
    String key = classData.toString();
    currentCacheMap = getCurrentCacheMap();
    String dataJson = currentCacheMap.get(key);
    return gson.fromJson(dataJson, classData);
  }

  private void saveCurrentMap() {
    String mapDataJson = gson.toJson(currentCacheMap);
    //mCryptoUtils.generateKey();
    //String encryptMapDataJson = mCryptoUtils.getData(mapDataJson, Cipher.ENCRYPT_MODE);
    mFileUtils.writeToFile(mapDataJson);
  }

  private Map<String, String> getCurrentCacheMap() {
    Map<String, String> currentMap = new HashMap<>();

    String encryptMapDataJson = mFileUtils.readFromFile();
    if (!encryptMapDataJson.isEmpty()) {
      //String mapDataJson = mCryptoUtils.getData(encryptMapDataJson, Cipher.DECRYPT_MODE);
      currentMap = gson.fromJson(encryptMapDataJson, new TypeToken<Map<String, String>>() {
      }.getType());
    }
    return currentMap;
  }

}
