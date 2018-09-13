package com.erlangshen.mvp.view.fragment

import android.app.ProgressDialog
import android.content.Context
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.WindowManager
import com.erlangshen.R
import com.erlangshen.adapter.NewsListAdapter
import com.erlangshen.base.BaseFragment
import com.erlangshen.mvp.model.LatestData
import com.erlangshen.mvp.model.MessageEvent
import com.erlangshen.mvp.presenter.NewsListPresenter
import com.erlangshen.mvp.view.INewsListView
import com.erlangshen.utils.DateUtils
import kotlinx.android.synthetic.main.news_list_layout.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class NewsListFragment : BaseFragment<NewsListPresenter>(), INewsListView {

    override val layoutId: Int = R.layout.news_list_layout
    var pDialog: ProgressDialog? = null
    var lastVisibleItem: Int = 0
    var wm: WindowManager? = null
    var layoutManager: LinearLayoutManager? = null
    var itemViewHeight: Int = 0
    val TAG: String = "abc"
    var tList: MutableList<LatestData.StoriesEntity> = mutableListOf()
    var date: String = ""

    override fun loadNewsList(stories: MutableList<LatestData.StoriesEntity>) {
        date = DateUtils.sysTime2
        tList!!.clear()
//        tList = stories.subList(0, 5)
        tList!!.addAll(stories)
        newsListRv.adapter = NewsListAdapter(mActivity as Context, tList!!)
    }

    override fun loadBeforeData(stories: MutableList<LatestData.StoriesEntity>) {
        tList!!.addAll(stories)
        newsListRv.adapter.notifyDataSetChanged()
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
        layoutManager = LinearLayoutManager(mActivity)

        newsListRv.layoutManager = layoutManager
        wm = mActivity!!.windowManager
        Log.e(TAG, "-- height:${wm!!.defaultDisplay.height} --")
        EventBus.getDefault().register(this)

        mvpPresenter?.requestNewsListData()

        mSwipeRl.setColorSchemeResources(R.color.swipe_color_1, R.color.swipe_color_1, R.color.swipe_color_1, R.color.swipe_color_1)
        mSwipeRl.setSize(SwipeRefreshLayout.DEFAULT)
        mSwipeRl.setProgressBackgroundColor(R.color.swipe_background_color)
        mSwipeRl.setProgressViewEndTarget(true, 100)
        mSwipeRl.setOnRefreshListener {
            showToast("xiala")
            if (mSwipeRl.isRefreshing) mSwipeRl.isRefreshing = false
            mvpPresenter!!.requestNewsListData()
        }
        /* 开始滚动（SCROLL_STATE_FLING），正在滚动(SCROLL_STATE_TOUCH_SCROLL), 已经停止（SCROLL_STATE_IDLE）*/
        newsListRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == newsListRv.adapter.itemCount) {//判断是否滑到最底部
                    showToast("load more")
                    date = DateUtils.getBeforeDay(date)
                    var entity = LatestData.StoriesEntity()
                    entity.title = date
                    entity.isDate = true
                    tList.add(entity)
                    mvpPresenter!!.requestBeforeData(date)
                }
            }

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                lastVisibleItem = layoutManager!!.findLastVisibleItemPosition()
            }
        })

    }

    /**
     * 最新新闻列表高度不足以填满手机屏高的时候，加载前一天的新闻一同展示
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun event(messageEvent: MessageEvent<MessageEvent.ViewHeight>) {
        if (messageEvent.messageStatus == "newsOk") {
            itemViewHeight = messageEvent.messageObj!!.height
            var recyclerHeight = itemViewHeight * newsListRv.adapter.itemCount
            Log.e(TAG, "--- recyclerHeight:$recyclerHeight ---")
            if (recyclerHeight < wm!!.defaultDisplay.height) {
                date = DateUtils.getBeforeDay(date)
                var entity = LatestData.StoriesEntity()
                entity.title = date
                entity.isDate = true
                tList.add(entity)
                mvpPresenter!!.requestBeforeData(date)
            }
        }
    }

    override fun initPresenter(): NewsListPresenter {
        return NewsListPresenter(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}