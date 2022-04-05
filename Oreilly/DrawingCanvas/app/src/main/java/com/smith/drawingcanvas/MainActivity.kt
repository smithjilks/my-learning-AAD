package com.smith.drawingcanvas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.smith.drawingcanvas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val drawing = Drawing(this)
        val customText = CustomTextView(this, null)
        setContentView(customText)
    }
}