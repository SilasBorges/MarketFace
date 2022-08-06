package com.example.markface

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.markface.databinding.ActivityPublishBinding
import com.google.firebase.firestore.FirebaseFirestore

class PublishActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPublishBinding
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPublishBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonPublish.setOnClickListener {

            val title = binding.textTitle.text.toString()
            val new = binding.textNews.text.toString()
            val date = binding.textDate.text.toString()
            val author = binding.textAuthor.text.toString()

            if (title.isEmpty() || new.isEmpty() || date.isEmpty() || author.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            } else {
                saveNews(title, new, date, author)
                }
            }
        }
    private fun saveNews(title: String, new: String, date: String, author: String) {
        val mapNews = hashMapOf(
            "title" to title,
            "new" to new,
            "date" to date,
            "author" to author
        )
        db.collection("news").document("new")
            .set(mapNews).addOnCompleteListener { tarefa ->
                if(tarefa.isSuccessful){
                    Toast.makeText(this, "Noticia publicada com sucesso!", Toast.LENGTH_SHORT).show()
                    clearField()
                }
            }.addOnFailureListener{}
    }
    private fun clearField(){
        binding.textTitle.setText("")
        binding.textNews.setText("")
        binding.textDate.setText("")
        binding.textAuthor.setText("")
    }
}