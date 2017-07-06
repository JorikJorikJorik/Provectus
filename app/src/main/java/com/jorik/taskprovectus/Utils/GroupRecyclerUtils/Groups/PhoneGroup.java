package com.jorik.taskprovectus.Utils.GroupRecyclerUtils.Groups;

import static com.jorik.taskprovectus.Model.Enum.DetailsInfoKind.PHONE;

import android.content.Context;
import com.jorik.taskprovectus.Model.POJO.InfoUserModel;
import com.jorik.taskprovectus.Network.DTO.UserDataDTO;
import com.jorik.taskprovectus.R;
import com.jorik.taskprovectus.View.Adapter.InfoUserAdapter;
import java.util.Arrays;

public class PhoneGroup extends BaseGroup<InfoUserAdapter, UserDataDTO> {

  public PhoneGroup(Context context, UserDataDTO data) {
    super(context, data);
  }

  @Override
  public String formationSubHeader() {
    return null;
  }

  @Override
  public Integer formationIcon() {
    return R.mipmap.ic_phone;
  }

  @Override
  public InfoUserAdapter formationAdapter() {
    InfoUserModel infoUserModelPhone = new InfoUserModel(getData().getPhone(), "Phone", R.mipmap.ic_made_call);
    InfoUserModel infoUserModelCell = new InfoUserModel(getData().getCell(), "Cell", R.mipmap.ic_made_call);
    return new InfoUserAdapter(getContext(), Arrays.asList(infoUserModelPhone, infoUserModelCell), PHONE);
  }

}
