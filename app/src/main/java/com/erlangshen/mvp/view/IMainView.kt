package com.erlangshen.mvp.view

import com.erlangshen.base.BaseView

interface IMainView : BaseView {
    /**
     * 初始化首页新闻列表
     */
    fun loadFragment()
}