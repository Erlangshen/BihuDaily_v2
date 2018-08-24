package com.erlangshen.mvp.presenter

import com.erlangshen.base.BasePresenter
import com.erlangshen.mvp.model.LatestData
import com.erlangshen.mvp.view.INewsListView

import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver

/**
 * 新闻列表
 */
class NewsListPresenter(iView: INewsListView) : BasePresenter<INewsListView>() {
    init {
        attachView(iView)
    }

    fun requestNewsListData() {
        view.showLoading()
        val latestData = apiStores.getLatestData("latest")
        addSubscription(latestData, object : DisposableObserver<LatestData>() {
            override fun onNext(latestData: LatestData) {
                view.loadNewsList(latestData.stories!!)
            }

            override fun onError(e: Throwable) {
                view.onError(e)
                view.hideLoading()
            }

            override fun onComplete() {
                view.onSuccess("请求成功!")
                view.hideLoading()
            }
        })
    }
}
