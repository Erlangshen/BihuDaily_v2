package com.erlangshen.mvp.view.adapter

import android.annotation.SuppressLint
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import com.erlangshen.mvp.view.fragment.HeadFragment

class HeadAdapter(var fm: FragmentManager, var headFragments: MutableList<HeadFragment>) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int) = headFragments[position]
    override fun getCount() = headFragments.size
    override fun getItemPosition(`object`: Any?) = PagerAdapter.POSITION_NONE
    @SuppressLint("RestrictedApi")
    fun setFragments(hFragments: MutableList<HeadFragment>) {
        var ft = fm.beginTransaction()
        hFragments.forEach {
            ft.remove(it)
            fm.fragments.remove(it)
        }
        ft.commit()
        ft = null
        fm.executePendingTransactions()
        headFragments = hFragments
        notifyDataSetChanged()
    }
}