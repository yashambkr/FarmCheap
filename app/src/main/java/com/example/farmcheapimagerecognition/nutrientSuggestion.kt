package com.example.farmcheapimagerecognition

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout

class nutrientSuggestion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nutrient_suggestion)
        findViewById<RelativeLayout>(R.id.nutrientLayout).visibility = View.VISIBLE
    }
}