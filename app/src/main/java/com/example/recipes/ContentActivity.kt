package com.example.recipes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipes.databinding.ActivityContentBinding

class ContentActivity : AppCompatActivity() {
    private lateinit var adapter: ContentAdapter
    private lateinit var binding: ActivityContentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)
        binding = ActivityContentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var cook = getIntent().getStringArrayExtra("cook")

        binding.foodName.text = getIntent().getStringExtra("id")
        binding.ingredient.text = "재료 :  " + getIntent().getStringExtra("content")!!.trim().replace(" ", ", ")
        binding.time.text = getIntent().getStringExtra("time")
        binding.backSpace.setOnClickListener { finish() }

        adapter = ContentAdapter(cook, applicationContext)
        binding.ContentRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
        binding.ContentRecyclerView.itemAnimator = DefaultItemAnimator()
        binding.ContentRecyclerView.adapter = adapter
    }
}