package com.jorik.taskprovectus.DataBase.TablesUtils;

import static com.jorik.taskprovectus.Model.Enum.PermissionState.FIRST;

import com.jorik.taskprovectus.DataBase.DataBaseUtils;
import com.jorik.taskprovectus.DataBase.Tables.PermissionTable;
import com.jorik.taskprovectus.Model.Enum.PermissionState;
import io.realm.RealmQuery;
import java.util.List;

public class PermissionTableUtils extends BasicTableUtils<PermissionTable> {

  private static final String PERMISSION_NAME_FIELD = "namePermission";

  private DataBaseUtils mDataBaseUtils;

  public PermissionTableUtils(DataBaseUtils dataBaseUtils) {
    mDataBaseUtils = dataBaseUtils;
  }

  public void updatePermissionValue(String permission, PermissionState state) {
    RealmQuery<PermissionTable> query = searchTable().equalTo(PERMISSION_NAME_FIELD, permission);
    mDataBaseUtils.updateValue(() -> {
      if (query != null) {
        query.findFirst().setPermissionStateValue(state.getValue());
      }
    });
  }

  @Override
  public <V> void createValue(V primaryKey) {
    PermissionTable item = mDataBaseUtils.createValue(PermissionTable.class, primaryKey);
    mDataBaseUtils.transaction(() -> item.setPermissionStateValue(FIRST.getValue()));
  }

  @Override
  public List<PermissionTable> getTableValues() {
    return mDataBaseUtils.getTableValues(PermissionTable.class);
  }

  @Override
  protected RealmQuery<PermissionTable> searchTable() {
    return mDataBaseUtils.searchTable(PermissionTable.class);
  }

}
