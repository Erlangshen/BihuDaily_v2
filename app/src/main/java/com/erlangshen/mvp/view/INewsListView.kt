package com.erlangshen.mvp.view

import com.erlangshen.base.BaseView
import com.erlangshen.mvp.model.LatestData

/**
 * 新闻列表
 */
interface INewsListView : BaseView {
    fun showLoading()
    fun hideLoading()
    fun loadNewsList(stories: List<LatestData.StoriesEntity>)
    fun onError(e: Throwable)
    fun onSuccess(text: String)
}
