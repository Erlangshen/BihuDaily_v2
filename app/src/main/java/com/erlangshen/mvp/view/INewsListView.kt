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
    fun loadNewsList(stories: MutableList<LatestData.StoriesEntity>)
    /**
     * 请求往日新闻
     */
    fun loadBeforeData(stories: MutableList<LatestData.StoriesEntity>)
    fun onError(e: Throwable)
    fun onSuccess(text: String)
}
