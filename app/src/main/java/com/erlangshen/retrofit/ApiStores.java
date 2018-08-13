package com.erlangshen.retrofit;

import com.erlangshen.mvp.model.LatestData;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiStores {
    @GET("news/{date}")
    Observable<LatestData> getLatestData(@Path("date") String date);
}
