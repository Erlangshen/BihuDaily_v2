package com.erlangshen.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.erlangshen.R;
import com.erlangshen.mvp.model.LatestData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.Holder>{
    private Context context;
    private List<LatestData.StoriesEntity> storiesEntities;

    public NewsListAdapter(Context context, List<LatestData.StoriesEntity> storiesEntities) {
        this.context = context;
        this.storiesEntities = storiesEntities;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.news_list_item,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        LatestData.StoriesEntity entity=storiesEntities.get(position);
        holder.menuListItemTv.setText(entity.getTitle());
        Glide.with(context)
                .load(entity.getImages().get(0))
                .error(R.mipmap.defaultcovers)
                .into(holder.menuListItemIv);
    }

    @Override
    public int getItemCount() {
        return storiesEntities.size();
    }

    class Holder extends RecyclerView.ViewHolder{
        @BindView(R.id.newsListItemTv)
        TextView menuListItemTv;
        @BindView(R.id.newsListItemIv)
        ImageView menuListItemIv;
        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(Holder.this,itemView);
        }
    }
}
