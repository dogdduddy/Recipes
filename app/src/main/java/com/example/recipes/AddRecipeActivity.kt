package com.example.recipes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.recipes.databinding.ActivityAddRecipeBinding
import com.example.recipes.databinding.ActivityRecipeListBinding
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
            if(binding.meetIn != null) add.put("육류","asd")
            if(binding.grainIn != null) add.put("곡물","asd")
            if(binding.seasoningIn != null) add.put("수산물","asd")
            if(binding.vegetableIn != null) add.put("채소","asd")
            if(binding.dairyProductIn != null) add.put("유제품","asd")
            if(binding.seasoningIn != null) add.put("양념","asd")
            if(binding.noodleIn != null) add.put("면","")
            if(binding.fruitIn != null) add.put("과일","")
            if(binding.meetIn != null) add.put("조미료","")
            if(binding.cookStepIn != null) add.put("요리","")
            if(binding.cooktimeIn != null) add.put("시간","")

            database!!.collection("Recipes").document(binding.foodNameIn.text.toString()).set(add)
                .addOnSuccessListener { documentReference ->
                    Log.e(TAG, "DocumentSnapshot written with ID: " + documentReference)
                    Toast.makeText(applicationContext, "Note has been added!", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    Log.e(TAG, "Error adding Note document", e)
                    Toast.makeText(applicationContext, "Note could not be added!", Toast.LENGTH_SHORT).show()
                }
        }
    }
}