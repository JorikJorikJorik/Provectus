package com.jorik.taskprovectus.DataBase;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import java.util.ArrayList;
import java.util.List;

public class DataBaseUtils {

  private Realm mRealm;

  public DataBaseUtils() {
    mRealm = Realm.getDefaultInstance();
  }

  public <T extends RealmObject, V> T createValue(Class<T> table, V primaryKey){
    transaction(() -> mRealm.createObject(table, primaryKey));
    List<T> value = getTableValues(table);
    return value.get(value.size() - 1);
  }

  public <T extends RealmObject> void deleteTable(Class<T> table){
    transaction(()-> mRealm.delete(table));
  }

  public <T extends RealmObject> void deleteValue(Class<T> table, int index) {
    getTableResults(table).get(index).deleteFromRealm();
  }

  public void updateValue(Runnable eventUpdate){
    transaction(()-> {
      if(eventUpdate != null)
        eventUpdate.run();
    });
  }

  public <T extends RealmObject> List<T> getTableValues(Class<T> table) {
    RealmResults<T> result = getTableResults(table);
    if (result == null || result.isEmpty()) {
      return null;
    }
    return convertResultToValues(result);
  }

  public <T extends RealmObject> T getValue(Class<T> table, int index) {
    return getTableResults(table).get(index);
  }

  public <T extends RealmObject> boolean checkIsEmptyTable(Class<T> table){
    return getTableResults(table).isEmpty();
  }

  public <T extends RealmObject> RealmQuery<T> searchTable(Class<T> table){
    return mRealm.where(table);
  }

  public <T extends RealmObject> RealmResults<T> getTableResults(Class<T> table){
    return mRealm.where(table).findAll();
  }

  public void transaction(Runnable event) {
    if (mRealm != null) {
      mRealm.beginTransaction();
      if (event != null) {
        event.run();
      }
      mRealm.commitTransaction();
    }
  }

  public void close() {
    if (mRealm != null) {
      mRealm.close();
    }
  }

  public Realm getRealm() {
    return mRealm;
  }

  private <T extends RealmObject> List<T> convertResultToValues(RealmResults<T> result) {
    List<T> values = new ArrayList<>();
    for (T item : result) {
      values.add(item);
    }
    return values;
  }
}
