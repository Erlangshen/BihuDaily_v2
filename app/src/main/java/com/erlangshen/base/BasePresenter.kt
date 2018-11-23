package com.erlangshen.base

import com.erlangshen.di.component.DaggerLatestDataComponent
import com.erlangshen.di.component.DaggerNetworkComponent
import com.erlangshen.retrofit.ApiStores
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import java.lang.ref.WeakReference
import javax.inject.Inject

abstract class BasePresenter<V : BaseView> {
    init {
        var networkComponent=DaggerNetworkComponent.builder().build()
        DaggerLatestDataComponent.builder()
                .networkComponent(networkComponent)
                .build()
                .inject(this as BasePresenter<BaseView>)
    }

    var mvpView: WeakReference<V>? = null
    @Inject
    lateinit var apiStores: ApiStores
    @Inject
    lateinit var cDisposable: CompositeDisposable

    fun attachView(view: V) {
        mvpView = WeakReference(view)
    }

    fun getView(): V = mvpView?.get()!!

    fun addSubscription(observable: Observable<*>, observer: DisposableObserver<*>) {
        cDisposable.add(observer)
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer as DisposableObserver<Any>)
    }

    fun detachView() {
        mvpView?.clear()
        mvpView = null
        unSubscribe()
    }

    //取消注册，以避免内存泄露
    fun unSubscribe() {
        cDisposable?.dispose()
    }
}