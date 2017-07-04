package com.jorik.taskprovectus.Utils;

import com.jorik.taskprovectus.Model.POJO.ErrorViewModel;

public interface StateScreenUtils<T> {

  void handleResult(T data);

  void handleError(ErrorViewModel error);

  void handleProgress();

  void stateScreen(boolean result, boolean error, boolean progress);
}
