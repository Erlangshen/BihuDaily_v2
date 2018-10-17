package com.erlangshen.mvp.view

import com.erlangshen.base.BaseView
import com.erlangshen.mvp.model.LatestData

/**
 * 新闻列表
 */
interface INewsListView : BaseView {
    fun showLoading()
    fun hideLoading()
    /**
     * 请求最新新闻
     */
    fun loadNewsList(data: LatestData)
    /**
     * 请求往日新闻
     */
    fun loadBeforeData(data: LatestData)
    fun onError(e: Throwable)
    fun onSuccess(text: String)
}
