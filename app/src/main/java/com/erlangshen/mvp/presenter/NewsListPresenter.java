package com.erlangshen.mvp.presenter;

import com.erlangshen.base.Constant;
import com.erlangshen.mvp.model.LatestData;
import com.erlangshen.mvp.view.INewListView;
import com.erlangshen.retrofit.ApiStores;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 新闻列表
 */
public class NewsListPresenter {
    private INewListView iView;
    private Retrofit retrofit;

    public NewsListPresenter(INewListView iView){
        this.iView=iView;
    }
    public void requestNewsListData(){
        iView.showLoading();
        OkHttpClient.Builder builder=new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS);
        retrofit=new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Constant.BASE_URL)
                .build();
        ApiStores apiStores = retrofit.create(ApiStores.class);
        Observable<LatestData> latestData = apiStores.getLatestData("latest");
        latestData.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<LatestData>() {
                    @Override
                    public void onNext(LatestData latestData) {
                        iView.loadNewsList(latestData.getStories());
                    }

                    @Override
                    public void onError(Throwable e) {
                        iView.onError(e);
                        iView.hideLoading();
                    }

                    @Override
                    public void onComplete() {
                        iView.onSuccess("请求成功!");
                        iView.hideLoading();
                    }
                });
    }
}
