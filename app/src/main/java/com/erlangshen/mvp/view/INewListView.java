package com.erlangshen.mvp.view;

import com.erlangshen.mvp.model.LatestData;

import java.util.List;
/**
 * 新闻列表
 * */
public interface INewListView {
    void showLoading();
    void hideLoading();
    void loadNewsList(List<LatestData.StoriesEntity> stories);
}
