package com.erlangshen.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment{
    protected P mvpPresenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, view);
        mvpPresenter=initPresenter();
        initData();
        return view;
    }
    protected abstract int getLayoutId();
    protected abstract void initData();
    /**初始化 Presenter*/
    protected abstract P initPresenter();

    @Override
    public void onDestroy() {
        mvpPresenter.detachView();
        super.onDestroy();
    }
}
