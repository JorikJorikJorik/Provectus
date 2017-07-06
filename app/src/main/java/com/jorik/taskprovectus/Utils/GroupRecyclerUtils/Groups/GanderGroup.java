package com.jorik.taskprovectus.Utils.GroupRecyclerUtils.Groups;

import static com.jorik.taskprovectus.Model.Enum.DetailsInfoKind.GANDER;
import static com.jorik.taskprovectus.Utils.StringUtils.firstLatterToUpper;

import android.content.Context;
import com.jorik.taskprovectus.Model.POJO.InfoUserModel;
import com.jorik.taskprovectus.Network.DTO.UserDataDTO;
import com.jorik.taskprovectus.R;
import com.jorik.taskprovectus.View.Adapter.InfoUserAdapter;
import java.util.Collections;

public class GanderGroup extends BaseGroup<UserDataDTO, InfoUserAdapter> {

  public GanderGroup(Context context, UserDataDTO data) {
    super(context, data);
  }

  @Override
  public String formationSubHeader() {
    return null;
  }

  @Override
  public Integer formationIcon() {
    return R.mipmap.ic_gander;
  }

  @Override
  public InfoUserAdapter formationAdapter() {
    InfoUserModel infoUserModelGander = new InfoUserModel(firstLatterToUpper(getData().getGender()), null, null);
    return new InfoUserAdapter(getContext(), Collections.singletonList(infoUserModelGander), GANDER);
  }
}
