package com.jorik.taskprovectus.Utils.GroupRecyclerUtils.Groups;

import static com.jorik.taskprovectus.Model.Enum.DetailsInfoKind.BIRTHDAY;

import android.content.Context;
import com.jorik.taskprovectus.Model.POJO.InfoUserModel;
import com.jorik.taskprovectus.Network.DTO.UserDataDTO;
import com.jorik.taskprovectus.R;
import com.jorik.taskprovectus.Utils.DateUtils;
import com.jorik.taskprovectus.View.Adapter.InfoUserAdapter;
import java.util.Collections;

public class BirthdayGroup extends BaseGroup<UserDataDTO, InfoUserAdapter> {

  private static final String OLD_FORMAT_DATE = "yyyy-MM-dd hh:mm:ss";
  private static final String NEW_FORMAT_DATE = "dd MMMM yyyy";

  public BirthdayGroup(Context context, UserDataDTO data) {
    super(context, data);
  }

  @Override
  public String formationSubHeader() {
    return null;
  }

  @Override
  public Integer formationIcon() {
    return R.mipmap.ic_birthday;
  }

  @Override
  public InfoUserAdapter formationAdapter() {
    InfoUserModel infoUserModelBirthday = new InfoUserModel(DateUtils.changeTypeFormated(getData().getDob(), OLD_FORMAT_DATE, NEW_FORMAT_DATE), null, null);
    return new InfoUserAdapter(getContext(), Collections.singletonList(infoUserModelBirthday), BIRTHDAY);
  }
}
