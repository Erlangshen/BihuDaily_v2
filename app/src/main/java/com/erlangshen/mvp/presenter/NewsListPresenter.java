package com.erlangshen.mvp.presenter;

import com.erlangshen.mvp.view.INewListView;

/**
 * 新闻列表
 */
public class NewsListPresenter {
    private INewListView iView;
    public NewsListPresenter(INewListView iView){
        this.iView=iView;
    }
    public void requestNewsListData(){
        iView.showLoading();

    }
}
