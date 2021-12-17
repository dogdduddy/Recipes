package com.example.recipes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import org.w3c.dom.Text

class FindAdapter(
    private val recipeList: MutableList<Array<String>>,
    private val context: Context,
    private val firestoreDB: FirebaseFirestore)
    : RecyclerView.Adapter<FindAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.item_find, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // recipe = {음식 이름, 재료, 요리 시간}
        val recipe = recipeList[position]

        holder!!.title.text = recipe[0]
        holder.content.text = "재료 : " +recipe[1]
        holder.time.text = recipe[2]

        //holder.edit.setOnClickListener { updateNote(recipe) }
        //holder.delete.setOnClickListener { deleteNote(recipe.id!!, position) }
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    inner class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        internal var title: TextView
        internal var content: TextView
        internal var time: TextView
        //internal var edit: ImageView
        //internal var delete: ImageView

        init {
            title = view.findViewById(R.id.findTitle)
            content = view.findViewById(R.id.findContent)
            time = view.findViewById(R.id.findTime)
            //edit = view.findViewById(R.id.ivEdit)
            //delete = view.findViewById(R.id.ivDelete)
        }
    }
}