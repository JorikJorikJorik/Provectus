package com.jorik.taskprovectus.Utils.GroupRecyclerUtils.Groups;

import static com.jorik.taskprovectus.Model.Enum.DetailsInfoKind.LOCATION;
import static com.jorik.taskprovectus.Utils.StringUtils.firstLatterToUpper;

import android.content.Context;
import com.jorik.taskprovectus.Model.POJO.InfoUserModel;
import com.jorik.taskprovectus.Network.DTO.LocationDTO;
import com.jorik.taskprovectus.Network.DTO.UserDataDTO;
import com.jorik.taskprovectus.R;
import com.jorik.taskprovectus.View.Adapter.InfoUserAdapter;
import java.util.Collections;

public class LocationGroup extends BaseGroup<UserDataDTO, InfoUserAdapter> {

  public LocationGroup(Context context, UserDataDTO data) {
    super(context, data);
  }

  @Override
  public String formationSubHeader() {
    return null;
  }

  @Override
  public Integer formationIcon() {
    return R.mipmap.ic_location;
  }

  @Override
  public InfoUserAdapter formationAdapter() {
    InfoUserModel infoUserModelLocation = new InfoUserModel(formationLocation(getData().getLocation()), null, null);
    return new InfoUserAdapter(getContext(), Collections.singletonList(infoUserModelLocation), LOCATION);
  }

  private String formationLocation(LocationDTO locationDTO) {
    return String.format("%s, %s %s", firstLatterToUpper(locationDTO.getState()), firstLatterToUpper(locationDTO.getCity()), firstLatterToUpper(locationDTO.getStreet()));
  }
}
