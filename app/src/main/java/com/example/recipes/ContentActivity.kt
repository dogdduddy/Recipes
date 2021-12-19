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
        var id = getIntent().getStringExtra("id")
        var content = getIntent().getStringExtra("content")
        var cook = getIntent().getStringArrayExtra("cook")
        var time = getIntent().getStringExtra("time")

        binding.backSpace.setOnClickListener { finish() }

        adapter = ContentAdapter(id, content, cook, time, applicationContext)
        binding.ContentRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
        binding.ContentRecyclerView.itemAnimator = DefaultItemAnimator()
        binding.ContentRecyclerView.adapter = adapter
    }
}