package com.example.farmcheapimagerecognition

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class suggestionMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_suggestion_menu)
        val diseaseSuggestions = findViewById<Button>(R.id.diseaseSuggestions).also {
            it.setOnClickListener(View.OnClickListener {
                val intent = Intent(this, diseaseSuggestion::class.java)
                startActivity(intent)
            })
        }
        val pestSuggestions = findViewById<Button>(R.id.pestSuggestions).also {
            it.setOnClickListener(View.OnClickListener {
                val intent = Intent(this, pestSuggestion::class.java)
                startActivity(intent)
            })
        }
        val nutrientSuggestions = findViewById<Button>(R.id.nutrientSuggestions).also {
            it.setOnClickListener(View.OnClickListener {
                val intent = Intent(this, nutrientSuggestion::class.java)
                startActivity(intent)
            })
        }
    }
}