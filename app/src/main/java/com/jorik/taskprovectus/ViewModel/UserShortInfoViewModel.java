package com.jorik.taskprovectus.ViewModel;

import android.content.Context;
import android.databinding.ObservableField;
import com.jorik.taskprovectus.Network.DTO.UserDataDTO;
import com.jorik.taskprovectus.Utils.GroupRecyclerUtils.FormationGroupUtils;
import com.jorik.taskprovectus.Utils.GroupRecyclerUtils.Groups.EmailGroup;
import com.jorik.taskprovectus.Utils.GroupRecyclerUtils.Groups.PhoneGroup;
import com.jorik.taskprovectus.View.Adapter.GroupRecyclerAdapter;

public class UserShortInfoViewModel {

  public final ObservableField<String> imageUser;
  public final ObservableField<String> fullNameUser;
  public final ObservableField<GroupRecyclerAdapter> shortInfoAdapter;

  private Context mContext;

  public UserShortInfoViewModel(Context context, UserDataDTO userDataDTO) {
    mContext = context;
    imageUser = new ObservableField<>(userDataDTO.getPicture().getThumbnail());
    fullNameUser = new ObservableField<>(String.format("%s %s", userDataDTO.getName().getFirst(), userDataDTO.getName().getLast()));
    shortInfoAdapter = new ObservableField<>(formationAdapter(userDataDTO));
  }

  private GroupRecyclerAdapter formationAdapter(UserDataDTO userDataDTO) {
    FormationGroupUtils mFormationGroupUtils = new FormationGroupUtils(mContext);
    mFormationGroupUtils.formationGroup(new PhoneGroup(mContext, userDataDTO));
    mFormationGroupUtils.formationGroup(new EmailGroup(mContext, userDataDTO));
    return mFormationGroupUtils.formationGroupAdapter();
  }

}
