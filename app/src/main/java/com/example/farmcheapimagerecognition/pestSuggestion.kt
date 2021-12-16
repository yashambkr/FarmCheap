package com.example.farmcheapimagerecognition

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout

class pestSuggestion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pest_suggestion)
        findViewById<RelativeLayout>(R.id.pestLayout).visibility = View.VISIBLE
    }
}