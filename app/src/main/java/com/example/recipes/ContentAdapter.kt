package com.example.recipes

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContentAdapter(
    private val cook: Array<String>?,
    private val context: Context
):RecyclerView.Adapter<ContentAdapter.ViewHolder>() {

/*
    if (a != null) {
        Log.d("Test2",a)
        Log.d("Test2",b.toString())
        if (c != null) {
            Log.d("Test2",c.get(0).toString())
        }
        Log.d("Test2",d.toString())

    }

 */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentAdapter.ViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.item_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContentAdapter.ViewHolder, position: Int) {
        var cook_step = cook!![position]
        // 요리법의 각 단계를 표시
        holder!!.order.text = (position+1).toString()+".   "
        // 각 단계별 요리방법을 설명
        holder!!.ContentCook.text = cook_step

    }

    override fun getItemCount(): Int {
        // 요리법의 단계를 나타냄
        return cook!!.size
    }
    inner class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        internal var order: TextView
        internal var ContentCook: TextView

        init {
            order = view.findViewById(R.id.order)
            ContentCook = view.findViewById(R.id.ContentCook)
        }
    }
}