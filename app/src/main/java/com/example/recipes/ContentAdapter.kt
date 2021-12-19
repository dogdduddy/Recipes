package com.example.recipes

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ContentAdapter(
    private val id: String?,
    private val content: String?,
    private val cook: Array<String>?,
    private val time: String?,
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
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ContentAdapter.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
    inner class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {

    }
}