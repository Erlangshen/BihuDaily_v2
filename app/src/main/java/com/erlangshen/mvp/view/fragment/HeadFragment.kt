package com.erlangshen.mvp.view.fragment

import com.bumptech.glide.Glide
import com.erlangshen.R
import com.erlangshen.base.BaseFragment
import com.erlangshen.mvp.presenter.HeadFragmentPresenter
import com.erlangshen.mvp.view.IHeadFragmentView
import kotlinx.android.synthetic.main.head_fragment.*

class HeadFragment : BaseFragment<HeadFragmentPresenter>(), IHeadFragmentView {
    var imageUrl: String? = null

    override val layoutId: Int = R.layout.head_fragment

    override fun initData() {
        mvpPresenter?.loadImage(imageUrl!!)
    }

    override fun initPresenter(): HeadFragmentPresenter {
        return HeadFragmentPresenter(this)
    }

    override fun initPicture(imageUrl: String) {
        Glide.with(activity).load(imageUrl).error(R.mipmap.defaultcovers).into(headFragmentImage)
    }

}