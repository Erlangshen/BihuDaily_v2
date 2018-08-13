package com.erlangshen.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.widget.Toast;

import butterknife.ButterKnife;

public abstract class BaseActivity extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initData();
    }

    protected abstract int getLayoutId();
    protected abstract void initData();
    protected void showToast(String text){
        Toast.makeText(BaseActivity.this, text, Toast.LENGTH_SHORT).show();
    }
}
