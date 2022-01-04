package com.example.recipes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipes.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore
import java.util.ArrayList

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

        binding.intentBtn.setOnClickListener {
            var intent = Intent(this, RecipeList::class.java)
            startActivity(intent)
        }
        
        binding.button.setOnClickListener{
            // 입력한 값들을 각가의 재료로 나눔
            Log.d(TAG,binding.findwindow.text.toString())
            var str = binding.findwindow.text.toString().split(",")
            // 식재료의 분류에 맞춰서 대입
            val refs = database.collection("Recipes")
            // 검색 통해 나온 레시피 이름을 담는 배열
            val recipeList = mutableListOf<Array<Any>>()

            // 입력한 재료만큼 반복 (str.size-1)인 이유는색 해당 반복문은 끝의 숫자를 포함하여 넣어주기 때문.

            /*
            // Array 형태의 값 검
            refs.whereArrayContains("ingredient","재료1" ).get().addOnSuccessListener { d ->
                for (docu in d) {
                    var a:ArrayList<String> = docu.get("ingredient") as ArrayList<String>
                    Log.d("TestBar", a[0])
                }
            }

             */
            refs.whereArrayContainsAny("ingredient", listOf("재료1","재료2")).get().addOnSuccessListener { d ->
                for (docu in d) {
                    var a:ArrayList<String> = docu.get("ingredient") as ArrayList<String>
                    Log.d("TestBar", a[0])
                }
            }



            for(i in 0..str.size-1) {
                if(ing_hash[str[i].trim()]!=null) {
                    refs.whereEqualTo(ing_hash[str[i].trim()].toString(), str[i].trim()).get()
                        .addOnSuccessListener { documents ->
                            for (document in documents) {
                                Log.d("MainTest : ",document.toString())
                                // 레시피 검색해서 나온 이름, 재료, 시간 저장
                                // note 객체처럼 만들어서 처리하려 했찌만 문서마다 필드의 종류가 달라서 안됨
                                var int_str:String = ""
                                for(j in kind) { if(document.get(j) != null) int_str += document.get(j).toString()+" " }
                                var a = document.get("요리") as List<String>
                                Log.d("Testtt : ",a[0])

                                recipeList.add(arrayOf(document.id, int_str, document.get("요리") as List<String>,
                                    document.get("시간").toString()))
                            }
                            // 원래는 반복문 밖에서 구현했지만. DB를 다 읽고 실행되는게 아니라 도중에 실행되서
                            // 원하는 데이터가 전부 들어오지 않을 때가 잇다.
                            // 현재는 불필요하게 많이 호출하게 되겠지만 그래도 데이터가 잘 나온다.
                            adapter = FindAdapter(recipeList, applicationContext, database)
                            binding.FindrecyclerView.layoutManager = LinearLayoutManager(applicationContext)
                            binding.FindrecyclerView.itemAnimator = DefaultItemAnimator()
                            binding.FindrecyclerView.adapter = adapter
                        }
                }
            }
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

private operator fun Any.get(i: Int) {

}
