package com.erlangshen.base;

import com.erlangshen.retrofit.ApiClient;
import com.erlangshen.retrofit.ApiStores;

import java.lang.ref.WeakReference;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class BasePresenter<V extends BaseView> {
    private CompositeDisposable cDisposable;//CompositeDisposable:一种disposable容器，它的dispose()方法可以切断所有订阅事件，从而防止内存泄漏
    protected WeakReference<V> mvpView;//使用弱引用，可以及时回收对象，防止内存泄漏
    protected ApiStores apiStores;

    protected void attachView(V mvpView) {
        this.mvpView = new WeakReference<>(mvpView);
        apiStores = ApiClient.getRetrofit().create(ApiStores.class);
    }

    protected void detachView() {
        if (mvpView != null) {
            mvpView.clear();
            mvpView = null;
            unSubscribe();
        }
    }

    protected V getView() {
        return mvpView.get();
    }

    public void addSubscription(Observable observable, DisposableObserver observer) {
        if (cDisposable == null)
            cDisposable = new CompositeDisposable();
        cDisposable.add(observer);
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    //取消注册，以避免内存泄露
    public void unSubscribe() {
        if (cDisposable != null)
            cDisposable.dispose();
    }
}
