package com.example.recipes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipes.databinding.ActivityMainBinding
import com.example.recipes.databinding.ActivityRecipeListBinding
import com.google.firebase.firestore.FirebaseFirestore

class RecipeList : AppCompatActivity() {
    private lateinit var adapter: FindAdapter
    private lateinit var database: FirebaseFirestore
    private lateinit var binding: ActivityRecipeListBinding
    private var recipeList = mutableListOf<Array<Any>>()
    private var TAG = "RecipeList"
    private val kind = arrayOf("육류", "곡물", "수산물", "채소","유제품","양념","면","과일","조미료")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)
        binding = ActivityRecipeListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = FirebaseFirestore.getInstance()

        database.collection("Recipes").get().addOnSuccessListener { documents ->
            for(doc in documents) {
                var int_str = ""
                //var temp = doc.get("요리").toString()
                //var cook = temp.substring(1,temp.length-1).split("#, ")
                for(j in kind) { if(doc.get(j) != null) int_str += doc.get(j).toString()+" " }
                recipeList.add(arrayOf(doc.id,int_str,doc.get("요리") as List<String>,doc.get("시간").toString()))
            }
            adapter = FindAdapter(recipeList, applicationContext, database)
            binding.ListRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
            binding.ListRecyclerView.itemAnimator = DefaultItemAnimator()
            binding.ListRecyclerView.adapter = adapter
        }

        binding.backSpace.setOnClickListener { finish() }
        binding.addBtn.setOnClickListener {
            var intent = Intent(this, AddRecipeActivity::class.java)
            startActivity(intent)ß
        }
    }
}