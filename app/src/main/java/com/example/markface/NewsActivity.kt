package com.example.markface

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.markface.databinding.ActivityNewsBinding
import com.google.firebase.firestore.FirebaseFirestore

class NewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsBinding
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toRecoverNew()
    }
    private fun toRecoverNew(){

        db.collection("news").document("new").get()
            .addOnCompleteListener { document ->
                if(document.isSuccessful){
                    val title = document.result.get("title").toString()
                    val new = document.result.get("new").toString()
                    val date = document.result.get("date").toString()
                    val author = document.result.get("author").toString()

                    binding.textTitle.text = title
                    binding.textNews.text = new
                    binding.textDate.text = date
                    binding.textAuthor.text = author
                }
            }
    }
}