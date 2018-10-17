package com.erlangshen.mvp.presenter

import com.erlangshen.base.BasePresenter
import com.erlangshen.mvp.view.IHeadFragmentView

class HeadFragmentPresenter(headFragmentView: IHeadFragmentView) : BasePresenter<IHeadFragmentView>() {
    init {
        attachView(headFragmentView)
    }

    fun loadImage(imageUrl: String) {
        view.initPicture(imageUrl)
    }
}