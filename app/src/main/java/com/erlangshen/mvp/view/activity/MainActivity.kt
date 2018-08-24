package com.erlangshen.mvp.view.activity


import com.erlangshen.R
import com.erlangshen.base.BaseActivity
import com.erlangshen.mvp.presenter.MainPresenter
import com.erlangshen.mvp.view.IMainView
import com.erlangshen.mvp.view.fragment.NewsListFragment

class MainActivity : BaseActivity<MainPresenter>(), IMainView {
    override val layoutId: Int = R.layout.main_layout

    override fun initData() {
        mvpPresenter?.initFragment()
    }

    override fun initPresenter(): MainPresenter {
        return MainPresenter(this)
    }

    override fun loadFragment() {
        var newsListFragment = NewsListFragment()
        var fManager = supportFragmentManager
        var fTransaction = fManager.beginTransaction()
        fTransaction.add(R.id.fragLinear, newsListFragment)
        fTransaction.commit()
    }
}
