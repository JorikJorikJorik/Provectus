package com.jorik.taskprovectus.Utils.GroupRecyclerUtils.Groups;

import static com.jorik.taskprovectus.Model.Enum.DetailsInfoKind.EMAIL;

import android.content.Context;
import com.jorik.taskprovectus.Model.POJO.InfoUserModel;
import com.jorik.taskprovectus.Network.DTO.UserDataDTO;
import com.jorik.taskprovectus.R;
import com.jorik.taskprovectus.View.Adapter.InfoUserAdapter;
import java.util.Collections;

public class EmailGroup extends BaseGroup<InfoUserAdapter, UserDataDTO> {

  public EmailGroup(Context context, UserDataDTO data) {
    super(context, data);
  }

  @Override
  public String formationSubHeader() {
    return null;
  }

  @Override
  public Integer formationIcon() {
    return R.mipmap.ic_email;
  }

  @Override
  public InfoUserAdapter formationAdapter() {
    InfoUserModel infoUserModelEmail = new InfoUserModel(getData().getEmail(), null, R.mipmap.ic_send_email);
    return new InfoUserAdapter(getContext(), Collections.singletonList(infoUserModelEmail), EMAIL);
  }

}
