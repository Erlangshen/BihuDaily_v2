package com.erlangshen.mvp.presenter

import com.erlangshen.base.BasePresenter
import com.erlangshen.mvp.model.LatestData
import com.erlangshen.mvp.view.INewsListView
import com.erlangshen.utils.DateUtils
import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

/**
 * 新闻列表
 */
class NewsListPresenter(iView: INewsListView) : BasePresenter<INewsListView>() {
    init {
        attachView(iView)
    }

    var dataList: MutableList<LatestData.StoriesEntity> = mutableListOf()
    @Inject
    lateinit var bihuData: LatestData
    var date: String = ""
    var isRequestDataOk = true
    /**
     * 请求最新新闻
     */
    fun requestNewsListData() {
        getView().showLoading()
        val latestData = apiStores.getLatestData("latest")
        addSubscription(latestData as Observable<*>, object : DisposableObserver<LatestData>() {
            override fun onNext(latestData: LatestData) {
                bihuData = latestData
                dataList.clear()
                dataList.addAll(latestData.stories!!)
                getView().loadNewsList(bihuData)
            }

            override fun onError(e: Throwable) {
                getView().onError(e)
                getView().hideLoading()
            }

            override fun onComplete() {
                getView().onSuccess("请求成功!")
                getView().hideLoading()
            }
        })
    }

    /**
     * 请求往日新闻
     */
    fun requestBeforeData() {
        getView().showLoading()
        if (isRequestDataOk) {
            date = DateUtils.getBeforeDay(date)
            var entity = LatestData.StoriesEntity()
            entity.title = date
            entity.isDate = true
            dataList.add(entity)
        }
        val latestData = apiStores.getBeforeData(date)
        addSubscription(latestData as Observable<*>, object : DisposableObserver<LatestData>() {
            override fun onNext(latestData: LatestData) {
                dataList.addAll(latestData.stories!!)
                bihuData.stories = dataList
                getView().loadBeforeData(bihuData)
            }

            override fun onError(e: Throwable) {
                getView().onError(e)
                getView().hideLoading()
                isRequestDataOk = false
            }

            override fun onComplete() {
                getView().onSuccess("请求成功!")
                getView().hideLoading()
                isRequestDataOk = true
            }
        })
    }
}
