package com.erlangshen.mvp.presenter

import com.erlangshen.base.BasePresenter
import com.erlangshen.mvp.view.ITestView

class TestPresenter(var tView:ITestView) : BasePresenter<ITestView>() {
    init {
        attachView(tView)
    }
    fun loadLv(strList: MutableList<String>){
        tView.initListView(strList)
    }
    fun refresh(){
        tView.refreshClick()
    }
}