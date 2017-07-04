package com.jorik.taskprovectus.ViewModel;

import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.SEND_SMS;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static com.jorik.taskprovectus.Model.Enum.ErrorViewType.EMPTY;

import android.content.Context;
import android.databinding.ObservableField;
import com.jorik.taskprovectus.DataBase.DataBaseUtils;
import com.jorik.taskprovectus.Model.POJO.ErrorViewModel;
import com.jorik.taskprovectus.Network.DTO.RandomResultsDTO;
import com.jorik.taskprovectus.Network.DTO.UserDataDTO;
import com.jorik.taskprovectus.Network.RestClient;
import com.jorik.taskprovectus.Network.Service.RandomService;
import com.jorik.taskprovectus.R;
import com.jorik.taskprovectus.Utils.CacheUtils;
import com.jorik.taskprovectus.Utils.ErrorViewUtils;
import com.jorik.taskprovectus.Utils.PermissionUtils;
import com.jorik.taskprovectus.Utils.ResourceUtils;
import com.jorik.taskprovectus.View.Adapter.RandUsersAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class RandUsersViewModel extends BaseViewModel {

  private static final int COUNT_USER_LOAD = 15;
  private static final String RESULTS_KEY = "results";

  public final ObservableField<Boolean> stateSwipeRefreshResult;
  public final ObservableField<Boolean> stateSwipeRefreshError;
  public final ObservableField<Boolean> visibleStateResult;
  public final ObservableField<Boolean> visibleStateError;
  public final ObservableField<Boolean> visibleStateProgress;
  public final ObservableField<RandUsersAdapter> randUserAdapter;
  public final ObservableField<ErrorViewModel> errorViewModel;

  private Context mContext;
  private CacheUtils<RandomResultsDTO> mCacheUtils;
  private ErrorViewUtils mErrorViewUtils;
  private DataBaseUtils mDataBaseUtils;
  private CompositeSubscription mSubscription;
  private ResourceUtils mResourceUtils;
  private PermissionUtils mPermissionUtils;
  private RandUsersAdapter mAdapter;

  private Throwable errorRequest;
  private boolean isLoaded;
  private int pageCount = 1;

  public RandUsersViewModel(Context context) {
    mContext = context;
    mCacheUtils = new CacheUtils<>(context);
    mErrorViewUtils = new ErrorViewUtils(context, () -> getRandUsers(true));
    mDataBaseUtils = new DataBaseUtils();
    mSubscription = new CompositeSubscription();
    mResourceUtils = ResourceUtils.with(context);
    mPermissionUtils = new PermissionUtils(context, formatPermissionList(), mDataBaseUtils);

    initDataBase(mDataBaseUtils);
    initSubscription(mSubscription);

    stateSwipeRefreshResult = new ObservableField<>();
    stateSwipeRefreshError = new ObservableField<>();
    visibleStateResult = new ObservableField<>();
    visibleStateError = new ObservableField<>();
    visibleStateProgress = new ObservableField<>();
    randUserAdapter = new ObservableField<>();
    errorViewModel = new ObservableField<>();
  }

  @Override
  public void onCreateView() {
    super.onCreateView();
    mPermissionUtils.workUtils();
  }

  @Override
  public void onResume() {
    super.onResume();
    getRandUsers(true);
  }

  public void uploadData() {
    getRandUsers(true);
  }

  private void getRandUsers(boolean isUpload) {

    RandomService randomService = RestClient.createService(RandomService.class);
    Subscription subscriptionRandUsers = Observable
      .just(isLoaded)
      .filter(data -> !data)
      .map(data -> isLoaded = true)
      .map(data -> formatQueryMapRandUser(isUpload))
      .doOnNext(data -> handleProgress())
      .flatMap(randomService::getRandomUserList)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .doOnNext((data) -> handleResettingError())
      .flatMap(data -> mCacheUtils.asyncWrite(data))
      .doOnError(this::handleThrowable)
      .onErrorResumeNext(mCacheUtils.asyncRead(RandomResultsDTO.class))
      .doOnNext(data -> handleResult(data, isUpload))
      .doAfterTerminate(() -> isLoaded = false)
      .subscribe(new Subscriber<RandomResultsDTO>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onNext(RandomResultsDTO randomResultsDTO) {
        }
      });

    mSubscription.add(subscriptionRandUsers);
  }


  private Map<String, Integer> formatQueryMapRandUser(boolean isUpload) {
    Map<String, Integer> queryMap = new HashMap<>();
    queryMap.put(RESULTS_KEY, isUpload ? COUNT_USER_LOAD * pageCount : COUNT_USER_LOAD);
    return queryMap;
  }

  private void handleResettingError() {
    mErrorViewUtils.dismissErrorSnackbar();
    handleThrowable(null);
  }

  private void handleThrowable(Throwable error) {
    errorRequest = error;
  }

  private void handleResult(RandomResultsDTO data, boolean isUpload) {
    if (data != null && data.getResults() != null) {
      List<UserDataDTO> userInfo = data.getResults();
      updateTitleToolbar(errorRequest != null ? mErrorViewUtils.formationShortError(errorRequest) : mResourceUtils.string(R.string.random_users_toolbar_title));
      if (userInfo.isEmpty())
        handleError(mErrorViewUtils.formationErrorEmpty(R.mipmap.ic_error_empty_user, R.string.random_users_error_empty_users));
      else {
        fillAdapter(userInfo, errorRequest != null, isUpload);
        stateScreen(true, false, false);
      }
    } else handleError(mErrorViewUtils.formationError(errorRequest));
  }

  private void handleError(ErrorViewModel errorView) {
    errorViewModel.set(errorView);
    stateScreen(false, true, false);
  }

  private void handleProgress() {
    ErrorViewModel errorModel = errorViewModel.get();
    boolean emptyError = false;
    if (errorModel != null) {
      emptyError = errorModel.getErrorType().equals(EMPTY);
    }
    boolean emptyAdapter = mAdapter == null && !emptyError;
    if (emptyAdapter || (errorModel != null && emptyAdapter))
      stateScreen(false, false, true);
  }

  private void stateScreen(boolean result, boolean error, boolean progress) {
    visibleStateResult.set(new Boolean(result)); //знаю, форма записи может показаться странной, но она необходима :D
    visibleStateError.set(new Boolean(error));
    visibleStateProgress.set(new Boolean(progress));

    if (result) {
      stateSwipeRefreshError.set(new Boolean(false));
      stateSwipeRefreshResult.set(new Boolean(false));
    }
  }

  private void fillAdapter(List<UserDataDTO> userInfo, boolean isCache, boolean isUpload) {

    userInfo.add(null);
    if (mAdapter == null) {
      mAdapter = new RandUsersAdapter(mContext, userInfo);
      randUserAdapter.set(mAdapter);
      pageCount++;
    } else if (isUpload) {
      mAdapter.setListData(userInfo);
      mAdapter.notifyDataSetChanged();
    } else if (!isCache) {
      mAdapter.getListData().remove(mAdapter.getListData().size() - 1);
      mAdapter.getListData().addAll(userInfo);
      mAdapter.notifyDataSetChanged();
      pageCount++;
    } else {
      mErrorViewUtils.showErrorSnackbar(errorRequest, this::loadingData, getCoordinatorForSnackbar());
      mAdapter.hideProgressLoading(false);
    }
  }

  public void loadingData() {
    mAdapter.hideProgressLoading(true);
    getRandUsers(false);
  }

  @Override
  public void requestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
    mPermissionUtils.handleRequestPermissionsResult(requestCode, permissions, grantResults);
  }

  private ArrayList<String> formatPermissionList() {
    return new ArrayList<>(Arrays.asList(WRITE_EXTERNAL_STORAGE, CALL_PHONE, SEND_SMS));
  }
}
