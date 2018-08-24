package com.erlangshen.mvp.presenter

import com.erlangshen.base.BasePresenter
import com.erlangshen.mvp.view.IMainView

class MainPresenter(var iView: IMainView) : BasePresenter<IMainView>() {
    init {
        attachView(iView)
    }
    fun initFragment(){
        iView.loadFragment()
    }
}