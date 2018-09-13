package com.erlangshen.mvp.view.activity

import android.support.v7.widget.LinearLayoutManager
import com.erlangshen.R
import com.erlangshen.adapter.TestAdapter
import com.erlangshen.base.BaseActivity
import com.erlangshen.mvp.presenter.TestPresenter
import com.erlangshen.mvp.view.ITestView
import kotlinx.android.synthetic.main.test_layout.*

class TestActivity : BaseActivity<TestPresenter>(), ITestView {

    var strList: MutableList<String> = mutableListOf()
    var i = 0
    override fun initListView(strList: MutableList<String>) {
        testRv.adapter = TestAdapter(this, strList)
    }

    override val layoutId = R.layout.test_layout

    override fun initData() {
        var lm:LinearLayoutManager=LinearLayoutManager(this)
        testRv.layoutManager=lm

        while (i < 10) {
            strList.add("条目 $i")
            i++
        }
        mvpPresenter!!.loadLv(strList)
        refresh.setOnClickListener {
            mvpPresenter!!.refresh()
        }
    }
    override fun refreshClick() {
        var j=i+10
        while (i<j){
            strList.add("条目 $i")
            i++
        }
        testRv.adapter.notifyDataSetChanged()
    }
    override fun initPresenter(): TestPresenter {
        return TestPresenter(this)
    }

}