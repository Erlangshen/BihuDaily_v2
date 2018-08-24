package com.erlangshen.mvp.view.fragment

import android.app.ProgressDialog
import android.content.Context
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.erlangshen.R
import com.erlangshen.adapter.NewsListAdapter
import com.erlangshen.base.BaseFragment
import com.erlangshen.mvp.model.LatestData
import com.erlangshen.mvp.presenter.NewsListPresenter
import com.erlangshen.mvp.view.INewsListView
import kotlinx.android.synthetic.main.news_list_layout.*

class NewsListFragment : BaseFragment<NewsListPresenter>(), INewsListView {
    var pDialog: ProgressDialog? = null
    override val layoutId: Int = R.layout.news_list_layout
    override fun loadNewsList(stories: List<LatestData.StoriesEntity>) {
        newsListRv.adapter = NewsListAdapter(mActivity as Context, stories)
    }

    override fun onError(e: Throwable) {
        showToast("加载数据失败")
    }

    override fun onSuccess(text: String) {
        showToast(text!!)
    }

    override fun showLoading() {
        pDialog?.show()
    }

    override fun hideLoading() {
        pDialog?.dismiss()
    }

    override fun initData() {
        pDialog = ProgressDialog(mActivity)
        pDialog?.setMessage("数据加载中...")
        newsListRv.layoutManager = LinearLayoutManager(mActivity)
        mvpPresenter?.requestNewsListData()
        mSwipeRl.setColorSchemeResources(R.color.swipe_color_1, R.color.swipe_color_1, R.color.swipe_color_1, R.color.swipe_color_1)
        mSwipeRl.setSize(SwipeRefreshLayout.DEFAULT)
        mSwipeRl.setProgressBackgroundColor(R.color.swipe_background_color)
        mSwipeRl.setProgressViewEndTarget(true, 100)
        mSwipeRl.setOnRefreshListener {
            showToast("xiala")
            if (mSwipeRl.isRefreshing) mSwipeRl.isRefreshing = false

        }
        newsListRv.setOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

            }

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

            }
        })
    }

    override fun initPresenter(): NewsListPresenter {
        return NewsListPresenter(this)
    }

}