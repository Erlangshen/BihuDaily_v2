package com.erlangshen.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget
import com.erlangshen.R
import com.erlangshen.mvp.model.LatestData
import com.erlangshen.mvp.model.MessageEvent
import com.erlangshen.utils.DateUtils
import kotlinx.android.synthetic.main.date_str_layout.view.*
import kotlinx.android.synthetic.main.foot_view.view.*
import kotlinx.android.synthetic.main.news_list_item.view.*
import org.greenrobot.eventbus.EventBus

class NewsListAdapter(var context: Context, var storiesEntities: MutableList<LatestData.StoriesEntity>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var loadMoreState = 0
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return if (1 == viewType) Holder(LayoutInflater.from(context).inflate(R.layout.news_list_item, parent, false))
        else if (0 == viewType) DateHolder(LayoutInflater.from(context).inflate(R.layout.date_str_layout, parent, false))
        else FootViewHolder(LayoutInflater.from(context).inflate(R.layout.foot_view, parent, false))
    }

    override fun getItemCount(): Int = storiesEntities.size + 1

    override fun getItemViewType(position: Int): Int {
        return if (position + 1 == itemCount) -1 else if (storiesEntities[position].isDate) 0 else 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is Holder) {
            var entity = storiesEntities[position]
            holder.itemView.newsListItemTv.text = entity.title
            Glide.with(context)
                    .load(entity.images!![0])
                    .error(R.mipmap.defaultcovers)
                    .into(object : GlideDrawableImageViewTarget(holder.itemView.newsListItemIv) {
                        override fun onResourceReady(resource: GlideDrawable?, animation: GlideAnimation<in GlideDrawable>?) {
                            super.onResourceReady(resource, animation)
                            if (position == 0) {
                                var me = MessageEvent<MessageEvent.ViewHeight>()
                                me.messageObj = MessageEvent.ViewHeight()
                                me.messageObj!!.height = holder.itemView.height
                                me.messageStatus = "newsOk"
                                EventBus.getDefault().post(me)
                            }
                        }
                    })//图片加载完成的监听
        } else if (holder is DateHolder) {
            var entity = storiesEntities[position]
            holder.itemView.dateStr.text = DateUtils.getDateStr(entity.title!!)
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
        notifyDataSetChanged()
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)
    class DateHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    class FootViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
