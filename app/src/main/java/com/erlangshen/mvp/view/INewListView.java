package com.erlangshen.mvp.view;

import com.erlangshen.base.BaseView;
import com.erlangshen.mvp.model.LatestData;

import java.util.List;
/**
 * 新闻列表
 * */
public interface INewListView extends BaseView{
    void showLoading();
    void hideLoading();
    void loadNewsList(List<LatestData.StoriesEntity> stories);
    void onError(Throwable e);
    void onSuccess(String text);
}
