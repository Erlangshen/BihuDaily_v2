package com.erlangshen.mvp.view

import com.erlangshen.base.BaseView

interface ITestView:BaseView{
    fun initListView(strList: MutableList<String>)
    fun refreshClick()
}