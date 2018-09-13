package com.erlangshen.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.erlangshen.R
import kotlinx.android.synthetic.main.date_str_layout.view.*

class TestAdapter(var con: Context, var strList: MutableList<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        var view: View =LayoutInflater.from(con).inflate(R.layout.date_str_layout, parent, false)
        return Holder(view)
    }

    override fun getItemCount() = strList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.dateStr.text = strList[position]
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)
}