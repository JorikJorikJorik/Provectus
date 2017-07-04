package com.jorik.taskprovectus.Network.Service;

import com.jorik.taskprovectus.Network.DTO.RandomResultsDTO;
import java.util.Map;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

public interface RandomService {
  @GET("./")
  Observable<RandomResultsDTO> getRandomUserList(@QueryMap Map<String, Integer> count);
}
