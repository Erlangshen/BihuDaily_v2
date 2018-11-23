package com.erlangshen.di.component

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
    fun getOKHttpClient(): OkHttpClient

    fun getRetrofit(): Retrofit

    fun getApiStores(): ApiStores

    fun getCompositeDisposable(): CompositeDisposable
}