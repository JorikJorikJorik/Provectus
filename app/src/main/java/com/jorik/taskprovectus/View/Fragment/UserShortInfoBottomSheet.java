package com.jorik.taskprovectus.View.Fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jorik.taskprovectus.Network.DTO.UserDataDTO;
import com.jorik.taskprovectus.R;
import com.jorik.taskprovectus.ViewModel.UserShortInfoViewModel;
import com.jorik.taskprovectus.databinding.FragmentUserShortInfoBottomSheetBinding;

public class UserShortInfoBottomSheet extends BottomSheetDialogFragment {

  private static final String USER_DATA_ARGS = "user_data_args";

  private UserDataDTO mUserDataDTO;

  public static UserShortInfoBottomSheet getInstance(UserDataDTO userDataDTO){
    UserShortInfoBottomSheet bottomSheet = new UserShortInfoBottomSheet();
    Bundle args = new Bundle();
    args.putParcelable(USER_DATA_ARGS, userDataDTO);
    bottomSheet.setArguments(args);
    return bottomSheet;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mUserDataDTO = getArguments().getParcelable(USER_DATA_ARGS);
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_user_short_info_bottom_sheet, container, false);
    UserShortInfoViewModel viewModel = new UserShortInfoViewModel(getActivity(), mUserDataDTO);
    FragmentUserShortInfoBottomSheetBinding binding = DataBindingUtil.bind(view);
    binding.setShortInfo(viewModel);

    return view;
  }
}
