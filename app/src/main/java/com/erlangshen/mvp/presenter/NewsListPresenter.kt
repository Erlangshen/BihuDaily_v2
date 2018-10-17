package com.erlangshen.mvp.presenter

import com.erlangshen.base.BasePresenter
import com.erlangshen.mvp.model.LatestData
import com.erlangshen.mvp.view.INewsListView
import com.erlangshen.utils.DateUtils
import com.erlangshen.utils.DateUtils.Companion.date
import io.reactivex.observers.DisposableObserver

/**
 * 新闻列表
 */
class NewsListPresenter(iView: INewsListView) : BasePresenter<INewsListView>() {
    init {
        attachView(iView)
    }

    var dataList: MutableList<LatestData.StoriesEntity> = mutableListOf()
    var bihuData: LatestData = LatestData()
    var date: String = ""
    var isRequestDataOk = true
    /**
     * 请求最新新闻
     */
    fun requestNewsListData() {
        view.showLoading()
        val latestData = apiStores.getLatestData("latest")
        addSubscription(latestData, object : DisposableObserver<LatestData>() {
            override fun onNext(latestData: LatestData) {
                bihuData = latestData
                dataList.clear()
                dataList.addAll(latestData.stories!!)
                view.loadNewsList(bihuData)
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

    /**
     * 请求往日新闻
     */
    fun requestBeforeData() {
        view.showLoading()
        if (isRequestDataOk) {
            date = DateUtils.getBeforeDay(date)
            var entity = LatestData.StoriesEntity()
            entity.title = date
            entity.isDate = true
            dataList.add(entity)
        }
        val latestData = apiStores.getBeforeData(date)
        addSubscription(latestData, object : DisposableObserver<LatestData>() {
            override fun onNext(latestData: LatestData) {
                dataList.addAll(latestData.stories!!)
                bihuData.stories = dataList
                view.loadBeforeData(bihuData)
            }

            override fun onError(e: Throwable) {
                view.onError(e)
                view.hideLoading()
                isRequestDataOk = false
            }

            override fun onComplete() {
                view.onSuccess("请求成功!")
                view.hideLoading()
                isRequestDataOk = true
            }
        })
    }
}
