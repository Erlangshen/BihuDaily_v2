package com.erlangshen.mvp.view;

import android.app.ProgressDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.erlangshen.R;
import com.erlangshen.base.BaseActivity;
import com.erlangshen.mvp.model.LatestData;
import com.erlangshen.mvp.presenter.NewsListPresenter;

import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements INewListView{
    @BindView(R.id.newsListRv)
    RecyclerView newsListRv;

    private ProgressDialog pDialog;
    private NewsListPresenter nPresenter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        LinearLayoutManager manager=new LinearLayoutManager(MainActivity.this);
        newsListRv.setLayoutManager(manager);
    }

    @Override
    protected void initData() {
        pDialog=new ProgressDialog(MainActivity.this);
        pDialog.setMessage("数据加载中...");
        nPresenter=new NewsListPresenter(MainActivity.this);
        nPresenter.requestNewsListData();
    }

    @Override
    public void showLoading() {
        pDialog.show();
    }

    @Override
    public void hideLoading() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    @Override
    public void loadNewsList(List<LatestData.StoriesEntity> stories) {

    }
}
