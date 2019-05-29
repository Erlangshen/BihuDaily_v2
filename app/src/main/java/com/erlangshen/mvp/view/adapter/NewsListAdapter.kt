package com.erlangshen.mvp.view.adapter

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.support.v4.app.FragmentActivity
import android.support.v4.view.ViewPager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget
import com.erlangshen.R
import com.erlangshen.mvp.model.LatestData
import com.erlangshen.mvp.model.MessageEvent
import com.erlangshen.mvp.view.fragment.HeadFragment
import com.erlangshen.utils.DateUtils
import kotlinx.android.synthetic.main.date_str_layout.view.*
import kotlinx.android.synthetic.main.foot_view.view.*
import kotlinx.android.synthetic.main.head_view.view.*
import kotlinx.android.synthetic.main.news_list_item.view.*
import org.greenrobot.eventbus.EventBus
import java.util.*

class NewsListAdapter(var context: Context, var data: LatestData) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var loadMoreState = 0
    var storiesEntities: MutableList<LatestData.StoriesEntity>? = null
    var headFragments: MutableList<HeadFragment> = mutableListOf()
    var headAdapter: HeadAdapter? = null
    var topStories: MutableList<LatestData.TopStoriesEntity> = mutableListOf()
    var count = 0
    var prePointPosition = 0
    var headVp: ViewPager? = null
    var timer: Timer? = null
    var task: TimerTask? = null
    var handler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            if (topStories.size > 1 && (count % topStories.size < topStories.size))
                headVp?.currentItem = count % topStories.size
        }
    }

    init {
        storiesEntities = data.stories
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
        return when (viewType) {
            0 -> HeadViewHolder(LayoutInflater.from(context).inflate(R.layout.head_view, parent, false))
            1 -> FootViewHolder(LayoutInflater.from(context).inflate(R.layout.foot_view, parent, false))
            2 -> DateHolder(LayoutInflater.from(context).inflate(R.layout.date_str_layout, parent, false))
            3 -> Holder(LayoutInflater.from(context).inflate(R.layout.news_list_item, parent, false))
            else -> null
        }
    }

    override fun getItemCount(): Int = storiesEntities!!.size + 2

    override fun getItemViewType(position: Int): Int {
        return when {
            position == 0 -> 0
            position + 1 == itemCount -> 1
            storiesEntities!![position - 1].isDate -> 2
            else -> 3
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is Holder) {
            var entity = storiesEntities!![position - 1]
            holder.itemView.newsListItemTv.text = entity.title
            Glide.with(context)
                    .load(entity.images!![0])
                    .error(R.mipmap.defaultcovers)
                    .into(object : GlideDrawableImageViewTarget(holder.itemView.newsListItemIv) {
                        override fun onResourceReady(resource: GlideDrawable?, animation: GlideAnimation<in GlideDrawable>?) {
                            super.onResourceReady(resource, animation)
                            if (position == 1) {
                                var me = MessageEvent<MessageEvent.ViewHeight>()
                                me.messageObj = MessageEvent.ViewHeight()
                                me.messageObj!!.height = holder.itemView.height
                                me.messageStatus = "newsOk"
                                EventBus.getDefault().post(me)
                            }
                        }
                    })//图片加载完成的监听
        } else if (holder is DateHolder) {
            var entity = storiesEntities!![position - 1]
            holder.itemView.dateStr.text = DateUtils.getDateStr(entity.title!!)
        } else if (holder is HeadViewHolder) {//headView
            topStories = data.top_stories!!
            if (topStories.size > 0) {
                holder.itemView.headIv.visibility = View.GONE
                holder.itemView.headVp.visibility = View.VISIBLE
                holder.itemView.headLinear.visibility = View.VISIBLE
                holder.itemView.titleTv.text = "今日热闻"
                if (holder.itemView.headLinear.childCount > 0) holder.itemView.headLinear.removeAllViews()
                headFragments?.clear()
                topStories.forEachIndexed { index, topStoriesEntity ->
                    var imageView = ImageView(context)
                    var params = LinearLayout.LayoutParams(dip2px(6.0f), dip2px(6.0f))
                    params.leftMargin = dip2px(8.0f)
                    imageView.layoutParams = params
                    imageView.setImageResource(R.mipmap.page)
                    if (index == 0) imageView.setImageResource(R.mipmap.page_now)
                    holder.itemView.headLinear.addView(imageView)
                    var hFragment = HeadFragment()
                    hFragment.imageUrl = topStoriesEntity.image
                    headFragments?.add(hFragment)
                }
                headVp = holder.itemView.headVp
                headVp?.clearOnPageChangeListeners()
                headVp?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                    override fun onPageScrollStateChanged(state: Int) {
                    }

                    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                    }

                    override fun onPageSelected(position: Int) {
                        if (topStories.size > 0) holder.itemView.headTv.text = topStories[position].title
                        var currentImage = holder.itemView.headLinear.getChildAt(position) as ImageView
                        var preImage = holder.itemView.headLinear.getChildAt(prePointPosition) as ImageView
                        currentImage.setImageResource(R.mipmap.page_now)
                        preImage.setImageResource(R.mipmap.page)
                        prePointPosition = position
                    }
                })
                if (topStories.size > 0) holder.itemView.headTv.text = topStories[0].title
                headAdapter = HeadAdapter((context as FragmentActivity).supportFragmentManager, headFragments)
                headVp?.adapter = headAdapter
                headAdapter?.setFragments(headFragments)
                stopTimer()
                startTimer()
            }
        } else {//footView
            if (0 == loadMoreState) {
                holder.itemView.footViewPb.visibility = View.GONE
                holder.itemView.footViewTv.text = "上拉加载更多"
            } else {
                holder.itemView.footViewPb.visibility = View.VISIBLE
                holder.itemView.footViewTv.text = "正在加载数据..."
            }
        }
    }

    public fun changeLoadMoreStatus(status: Int) {
        loadMoreState = status
        storiesEntities = data.stories
        stopTimer()
        startTimer()
        notifyDataSetChanged()
    }

    /**
     * 开始Timer任务
     */
    fun startTimer() {
        if (timer == null) {
            timer = Timer()
        }
        if (task == null) {
            task = object : TimerTask() {
                override fun run() {
                    handler.sendEmptyMessage(count)
                    count++
                }
            }
        }
        if (timer != null && task != null) {
            timer?.schedule(task, 3000, 3000)
        }
    }

    /**
     * 暂停Timer任务
     */
    fun stopTimer() {
        if (timer != null) {
            timer?.cancel()
            timer = null
        }
        if (task != null) {
            task?.cancel()
            task = null
        }
        count = 0
    }

    /**
     * dp转成px
     */
    fun dip2px(dipValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dipValue * scale + 0.5f).toInt()
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)
    class DateHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    class FootViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    class HeadViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
