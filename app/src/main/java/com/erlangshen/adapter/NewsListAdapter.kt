package com.erlangshen.adapter;

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.erlangshen.R
import com.erlangshen.mvp.model.LatestData
import kotlinx.android.synthetic.main.news_list_item.view.*

class NewsListAdapter(var context: Context, var storiesEntities: List<LatestData.StoriesEntity>) : RecyclerView.Adapter<NewsListAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): Holder {
        var view = LayoutInflater.from(context).inflate(R.layout.news_list_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = storiesEntities.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        var entity = storiesEntities[position]
        holder.itemView.newsListItemTv.text=entity.title
        Glide.with(context)
                .load(entity.images!![0])
                .error(R.mipmap.defaultcovers)
                .into(holder.itemView.newsListItemIv)

    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
