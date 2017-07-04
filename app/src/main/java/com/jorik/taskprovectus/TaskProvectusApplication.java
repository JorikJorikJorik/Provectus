package com.jorik.taskprovectus;

import android.app.Application;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class TaskProvectusApplication extends Application {

  private static final int SCHEME_VERSION = 0;
  private static TaskProvectusApplication sTaskProvectusApplication = null;

  public static TaskProvectusApplication getInstance() {
    if (sTaskProvectusApplication == null) {
      sTaskProvectusApplication = new TaskProvectusApplication();
    }
    return sTaskProvectusApplication;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    Realm.init(this);
    RealmConfiguration configuration = new RealmConfiguration.Builder()
        .name(Realm.DEFAULT_REALM_NAME)
        .schemaVersion(SCHEME_VERSION)
        .deleteRealmIfMigrationNeeded()
        .build();

    Realm.setDefaultConfiguration(configuration);
  }
}
