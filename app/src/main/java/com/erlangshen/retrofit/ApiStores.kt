package com.erlangshen.retrofit

import com.erlangshen.mvp.model.LatestData

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiStores {
    @GET("news/{date}")
    fun getLatestData(@Path("date") date: String): Observable<LatestData>
}
