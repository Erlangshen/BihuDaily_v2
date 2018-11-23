package com.erlangshen.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.view.Window
import android.widget.Toast

abstract class BaseActivity<P : BasePresenter<*>> : FragmentActivity() {
    protected var mvpPresenter: P? = null

    protected abstract val layoutId: Int
    public override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        mvpPresenter = initPresenter()
        initData()
    }

    protected abstract fun initData()

    /**初始化 Presenter */
    protected abstract fun initPresenter(): P

    override fun onDestroy() {
        super.onDestroy()
        mvpPresenter?.detachView()
    }
    fun Context.showToast(txt:String,length:Int=Toast.LENGTH_SHORT){
        Toast.makeText(this,txt,length).show()
    }
}
