package com.erlangshen.base

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast


abstract class BaseFragment<P : BasePresenter<*>> : Fragment() {
    protected var mvpPresenter: P? = null
    protected var mActivity: Activity? = null

    protected abstract val layoutId: Int
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(layoutId, container, false)
        mActivity = activity
        mvpPresenter = initPresenter()
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }

    protected abstract fun initData()
    /**初始化 Presenter */
    protected abstract fun initPresenter(): P

    fun Fragment.showToast(txt: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(mActivity, txt, duration).show()
    }

    override fun onDestroy() {
        mvpPresenter?.detachView()
        super.onDestroy()
    }

}
