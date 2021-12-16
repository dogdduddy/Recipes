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
            val recipeList = mutableListOf<String>()
            // 입력한 재료만큼 반복 (str.size-1)인 이유는 해당 반복문은 끝의 숫자를 포함하여 넣어주기 때문.
            for(i in 0..str.size-1) {
                if(ing_hash[str[i]]!=null) {
                    refs.whereEqualTo(ing_hash[str[i]].toString(), str[i]).get()
                        .addOnSuccessListener { documents ->
                            for (document in documents) {
                                // 레시피 검색해서 나온 이름을 저장
                                recipeList.add(document.id)
                                // Log.d(TAG, document.id.toString())
                            }
                        }
                }
            }
            Log.d(TAG, recipeList.toString())

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