package com.jorik.taskprovectus.Utils.GroupRecyclerUtils.Groups;

import static com.jorik.taskprovectus.Model.Enum.DetailsInfoKind.NATIONALITY;

import android.content.Context;
import com.jorik.taskprovectus.Model.POJO.InfoUserModel;
import com.jorik.taskprovectus.Network.DTO.UserDataDTO;
import com.jorik.taskprovectus.R;
import com.jorik.taskprovectus.Utils.ResourceUtils;
import com.jorik.taskprovectus.View.Adapter.InfoUserAdapter;
import java.util.Collections;
import java.util.Locale;

public class NationalityGroup extends BaseGroup<UserDataDTO, InfoUserAdapter> {

  public NationalityGroup(Context context, UserDataDTO data) {
    super(context, data);
  }

  @Override
  public String formationSubHeader() {
    return null;
  }

  @Override
  public Integer formationIcon() {
    return R.mipmap.ic_nationality;
  }

  @Override
  public InfoUserAdapter formationAdapter() {
    Locale locale = new Locale(ResourceUtils.with(getContext()).string(R.string.empty_string), getData().getNat());
    InfoUserModel infoUserModelNationality = new InfoUserModel(locale.getDisplayCountry(), null, null);
    return new InfoUserAdapter(getContext(), Collections.singletonList(infoUserModelNationality), NATIONALITY);
  }
}
