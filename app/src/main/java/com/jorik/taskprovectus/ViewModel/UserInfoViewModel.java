package com.jorik.taskprovectus.ViewModel;

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

  public final ObservableField<GroupRecyclerAdapter> adapterUserInfo;

  private Context mContext;

  public UserInfoViewModel(Context context, UserDataDTO dataDTO) {
    mContext = context;
    adapterUserInfo = new ObservableField<>(formationAdapter(dataDTO));
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
