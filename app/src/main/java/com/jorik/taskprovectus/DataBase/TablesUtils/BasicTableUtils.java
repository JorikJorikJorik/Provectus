package com.jorik.taskprovectus.DataBase.TablesUtils;

import io.realm.RealmObject;
import io.realm.RealmQuery;
import java.util.List;

public abstract class BasicTableUtils<T extends RealmObject> {

  public abstract <V> void createValue(V primaryKey);
  public abstract List<T> getTableValues();
  protected abstract RealmQuery<T> searchTable();


}
