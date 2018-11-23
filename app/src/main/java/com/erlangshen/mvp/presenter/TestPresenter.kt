package com.erlangshen.mvp.presenter

import com.erlangshen.base.BasePresenter
import com.erlangshen.mvp.view.ITestView

class TestPresenter(tView:ITestView) : BasePresenter<ITestView>() {
    init {
        attachView(tView)
    }
    fun loadLv(strList: MutableList<String>){
        getView().initListView(strList)
    }
    fun refresh(){
        getView().refreshClick()
    }
}