package com.example.recipes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
gitimport com.example.recipes.databinding.ActivityAddRecipeBinding
import com.google.firebase.firestore.FirebaseFirestore

class AddRecipeActivity : AppCompatActivity() {
    private lateinit var database: FirebaseFirestore
    private lateinit var binding: ActivityAddRecipeBinding
    private var TAG = "AddRecipeActivity"

    //레시피명 / "육류", "곡물", "수산물", "채소","유제품","양념",
    //"면","과일","조미료" / 요리 방법 / 시간 -->
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_recipe)
        binding = ActivityAddRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = FirebaseFirestore.getInstance()

        binding.addBtn.setOnClickListener {
            var add = HashMap<String, Any>()
            if(!binding.meetIn.text.toString().equals("")) add.put("육류", binding.meetIn.text.toString())
            if(!binding.grainIn.text.toString().equals("")) add.put("곡물", binding.grainIn.text.toString())
            if(!binding.seasoningIn.text.toString().equals("")) add.put("양념",binding.seasoningIn.text.toString())
            if(!binding.vegetableIn.text.toString().equals("")) add.put("채소",binding.vegetableIn.text.toString())
            if(!binding.dairyProductIn.text.toString().equals("")) add.put("유제품",binding.dairyProductIn.text.toString())
            if(!binding.seasoningIn.text.toString().equals("")) add.put("수산물",binding.seasoningIn.text.toString())
            if(!binding.noodleIn.text.toString().equals("")) add.put("면",binding.noodleIn.text.toString())
            if(!binding.fruitIn.text.toString().equals("")) add.put("과일",binding.fruitIn.text.toString())
            if(!binding.fruitIn.text.toString().equals("")) add.put("조미료",binding.CondimentIn.text.toString())
            if(!binding.cookStepIn.text.toString().equals("")) {
                var add_list = mutableListOf<String>()
                var temp = ""
                val Step = binding.cookStepIn.text.toString()
                Log.d(TAG,Step.length.toString())
                for(i in 1..Step.length-1) {
                    if (Step[i].equals('#')) {
                        add_list.add(temp)
                        temp=""
                    }
                    else {
                        temp = temp + Step[i]
                        if(i == Step.length-1) { add_list.add(temp)}
                    }
                }
                add.put("요리",add_list)
            }
            if(!binding.cooktimeIn.text.toString().equals("")) add.put("시간",binding.cooktimeIn.text.toString())
            Log.d(TAG,add.size.toString())
            if(add.size > 0) {
                database!!.collection("Recipes").document(binding.foodNameIn.text.toString())
                    .set(add)
                    .addOnSuccessListener { documentReference ->
                        Log.e(TAG, "DocumentSnapshot written with ID: " + documentReference)
                        Toast.makeText(
                            applicationContext,
                            "Note has been added!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    .addOnFailureListener { e ->
                        Log.e(TAG, "Error adding Note document", e)
                        Toast.makeText(
                            applicationContext,
                            "Note could not be added!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            }
            finish()
        }
    }
}