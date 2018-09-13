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
import kotlinx.android.synthetic.main.news_list_item.view.*
import org.greenrobot.eventbus.EventBus

class NewsListAdapter(var context: Context, var storiesEntities: MutableList<LatestData.StoriesEntity>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return if (1 == viewType) Holder(LayoutInflater.from(context).inflate(R.layout.news_list_item, parent, false))
        else DateHolder(LayoutInflater.from(context).inflate(R.layout.date_str_layout, parent, false))
    }

    override fun getItemCount(): Int = storiesEntities.size

    override fun getItemViewType(position: Int): Int {
        return if (storiesEntities.get(position).isDate) 0 else 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var entity = storiesEntities[position]
        if (holder is Holder) {
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
        } else {
            holder.itemView.dateStr.text=DateUtils.getDateStr(entity.title!!)
        }
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)
    class DateHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
