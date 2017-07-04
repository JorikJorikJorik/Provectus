package com.jorik.taskprovectus.Network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

  private static final int TIME_TIME = 60;
  private static final String BASE_URL = "https://randomuser.me/api/";
  private static Gson gsonDate = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

  public static <T> T createService(Class<T> service) {

    OkHttpClient client = new OkHttpClient.Builder()
      .writeTimeout(TIME_TIME, TimeUnit.SECONDS)
      .readTimeout(TIME_TIME, TimeUnit.SECONDS)
      .connectTimeout(TIME_TIME, TimeUnit.SECONDS)
      .build();

    Retrofit retrofit = new Retrofit.Builder()
      .baseUrl(BASE_URL)
      .addConverterFactory(GsonConverterFactory.create(gsonDate))
      .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
      .client(client)
      .build();

    return retrofit.create(service);
  }

}
