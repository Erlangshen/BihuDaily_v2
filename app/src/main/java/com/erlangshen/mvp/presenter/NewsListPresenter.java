package com.erlangshen.mvp.presenter;

import com.erlangshen.base.BasePresenter;
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
public class NewsListPresenter extends BasePresenter<INewListView>{
    public NewsListPresenter(INewListView iView) {
        attachView(iView);
    }

    public void requestNewsListData() {
        getView().showLoading();
        Observable<LatestData> latestData = apiStores.getLatestData("latest");
        addSubscription(latestData,new DisposableObserver<LatestData>() {
            @Override
            public void onNext(LatestData latestData) {
                getView().loadNewsList(latestData.getStories());
            }

            @Override
            public void onError(Throwable e) {
                getView().onError(e);
                getView().hideLoading();
            }

            @Override
            public void onComplete() {
                getView().onSuccess("请求成功!");
                getView().hideLoading();
            }
        });
    }
}
