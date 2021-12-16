package com.example.farmcheapimagerecognition

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.farmcheapimagerecognition.ml.FarmModel
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer

class RecognitionPage : AppCompatActivity() {
    lateinit var bitmap: Bitmap
    lateinit var imageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recognition_page)

        imageView = findViewById(R.id.imageView2)
        val suggButton : Button = findViewById(R.id.suggestionButton)
        suggButton.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, suggestionMenu::class.java)
            startActivity(intent)
        })
        val fileName = "labels.txt"
        val inputString = application.assets.open(fileName).bufferedReader().use { it.readText() }
        var townlist = inputString.split("\n")

        var textView: TextView = findViewById(R.id.textView)


        var select: Button = findViewById(R.id.select)
        select.setOnClickListener(View.OnClickListener {
            var intent: Intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"

            startActivityForResult(intent, 100)

        })

        var predict: Button = findViewById(R.id.predict)
        predict.setOnClickListener(View.OnClickListener {
            var resize: Bitmap = Bitmap.createScaledBitmap(bitmap, 224, 224, true)
            val model = FarmModel.newInstance(this)
            var theBuffer = TensorImage.fromBitmap(resize)
            var byteBuffer = theBuffer.buffer
// Creates inputs for reference.
            val inputFeature0 =
                TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.UINT8)
            inputFeature0.loadBuffer(byteBuffer)

// Runs model inference and gets result.
            val outputs = model.process(theBuffer)
                .probabilityAsCategoryList.apply {
                    sortByDescending { it.score } // Sort with highest confidence first
                }.take(Int.MAX_VALUE) // take the top results
            //val outputs = model.process(theBuffer)
            //val probability = outputs.probabilityAsCategoryList.apply { sortByDescending { it.score } }
            // var max = getMax(probability.[0])
            var text = outputs[0].label.toString()
            if(text.equals("good"))
                textView.setText("Your Crop is good")
            else if(text.equals("nutrient"))
                textView.setText("Your Crop need nutrients")
            else if(text.equals("Pest"))
                textView.setText("Your Crop is damaged by pests")
            else if(text.equals("disease"))
                textView.setText("Your Crop is diseased")

            //textView.setText(outputs[0].label.toString())
// Releases model resources if no longer used.
            model.close()
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        imageView.setImageURI(data?.data)

        var uri: Uri? = data?.data
        bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
    }

    fun getMax(arr: FloatArray): Int {
        var index = 0
        var min = 0.0f

        for (i in 0..3) {
            if (arr[i] > min) {
                index = i
                min = arr[i]
            }
        }
        return index

    }
}