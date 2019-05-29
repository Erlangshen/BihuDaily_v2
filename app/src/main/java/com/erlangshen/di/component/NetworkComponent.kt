package com.erlangshen.di.component

import com.erlangshen.base.BasePresenter
import com.erlangshen.base.BaseView
import com.erlangshen.di.module.NetworkModule
import com.erlangshen.retrofit.ApiStores
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface NetworkComponent {
    fun inject(basePresenter: BasePresenter<BaseView>)
}