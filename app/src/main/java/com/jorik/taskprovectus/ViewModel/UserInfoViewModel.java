package com.jorik.taskprovectus.ViewModel;

import static com.jorik.taskprovectus.Utils.StringUtils.firstLatterToUpper;

import android.content.Context;
import android.databinding.ObservableField;
import com.jorik.taskprovectus.Network.DTO.UserDataDTO;
import com.jorik.taskprovectus.Utils.GroupRecyclerUtils.FormationGroupUtils;
import com.jorik.taskprovectus.Utils.GroupRecyclerUtils.Groups.BirthdayGroup;
import com.jorik.taskprovectus.Utils.GroupRecyclerUtils.Groups.EmailGroup;
import com.jorik.taskprovectus.Utils.GroupRecyclerUtils.Groups.GanderGroup;
import com.jorik.taskprovectus.Utils.GroupRecyclerUtils.Groups.LocationGroup;
import com.jorik.taskprovectus.Utils.GroupRecyclerUtils.Groups.NationalityGroup;
import com.jorik.taskprovectus.Utils.GroupRecyclerUtils.Groups.PhoneGroup;
import com.jorik.taskprovectus.View.Adapter.GroupRecyclerAdapter;

public class UserInfoViewModel extends BaseViewModel {

  public final ObservableField<String> imageUser;
  public final ObservableField<GroupRecyclerAdapter> adapterUserInfo;

  private Context mContext;
  private UserDataDTO mUserDataDTO;

  public UserInfoViewModel(Context context, UserDataDTO dataDTO) {
    mContext = context;
    mUserDataDTO = dataDTO;
    imageUser = new ObservableField<>(dataDTO.getPicture().getLarge());
    adapterUserInfo = new ObservableField<>(formationAdapter(dataDTO));
  }

  @Override
  public void onResume() {
    super.onResume();
    updateTitleToolbar(String.format("%s %s", firstLatterToUpper(mUserDataDTO.getName().getFirst()), firstLatterToUpper(mUserDataDTO.getName().getLast())));
  }

  private GroupRecyclerAdapter formationAdapter(UserDataDTO dataDTO) {
    FormationGroupUtils formationGroupUtils = new FormationGroupUtils(mContext);
    formationGroupUtils.formationGroup(new PhoneGroup(mContext, dataDTO));
    formationGroupUtils.formationGroup(new EmailGroup(mContext, dataDTO));
    formationGroupUtils.formationGroup(new BirthdayGroup(mContext, dataDTO));
    formationGroupUtils.formationGroup(new GanderGroup(mContext, dataDTO));
    formationGroupUtils.formationGroup(new LocationGroup(mContext, dataDTO));
    formationGroupUtils.formationGroup(new NationalityGroup(mContext, dataDTO));
    return formationGroupUtils.formationGroupAdapter();
  }
}
