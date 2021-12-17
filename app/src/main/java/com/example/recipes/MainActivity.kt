package com.example.recipes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipes.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: FindAdapter
    private lateinit var database: FirebaseFirestore
    private lateinit var binding: ActivityMainBinding
    private var TAG = "MainActivity"
    private var ing_hash = HashMap<String,String>()
    private val kind = arrayOf("육류", "곡물", "수산물", "채소","유제품","양념","면","과일","조미료")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = FirebaseFirestore.getInstance() // DB reference
        loadIngredient()
        binding.button.setOnClickListener{
            // 입력한 값들을 각가의 재료로 나눔
            var str = binding.findwindow.text.toString().split(",")
            // 식재료의 분류에 맞춰서 대입
            val refs = database.collection("Recipes")
            // 검색 통해 나온 레시피 이름을 담는 배열
            val recipeList = mutableListOf<Array<String>>()
            // 입력한 재료만큼 반복 (str.size-1)인 이유는 해당 반복문은 끝의 숫자를 포함하여 넣어주기 때문.
            for(i in 0..str.size-1) {
                if(ing_hash[str[i].trim()]!=null) {
                    refs.whereEqualTo(ing_hash[str[i].trim()].toString(), str[i].trim()).get()
                        .addOnSuccessListener { documents ->
                            for (document in documents) {
                                // 레시피 검색해서 나온 이름, 재료, 시간 저장
                                var int_str:String = ""
                                for(j in kind) { if(document.get(j) != null) int_str += document.get(j).toString()+" " }
                                recipeList.add(arrayOf(document.id.toString(), int_str, document.get("시간").toString()))
                            }
                        }
                }
            }
            adapter = FindAdapter(recipeList, applicationContext, database)
            binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
            binding.recyclerView.itemAnimator = DefaultItemAnimator()
            binding.recyclerView.adapter = adapter
        }
    }

    private fun loadIngredient() {
        database.collection("Ingredients").document("ingredients").get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    // 재료가 모두 적힌 Document 읽기
                    var data = document.data
                    if (data != null) {
                        // 모든 내용을 hashMap에 저장
                        for(i in data) ing_hash.put(i.key.toString(),i.value.toString())
                    }
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }
    }
}