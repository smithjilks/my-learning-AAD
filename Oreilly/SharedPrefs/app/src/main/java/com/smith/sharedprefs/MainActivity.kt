package com.smith.sharedprefs

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.smith.sharedprefs.databinding.ActivityMainBinding

const val MESSAGE_ID = "message_prefs"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener{
            val message = binding.textEditText.text.toString()
            val sharedPreferences: SharedPreferences = getSharedPreferences(MESSAGE_ID, MODE_PRIVATE)
            val editor: SharedPreferences.Editor =sharedPreferences.edit()

            editor.putString("message",message)
            editor.apply() // save to disk.
        }

        // Get data back from shared prefs
        val getSharedPrefs: SharedPreferences = getPreferences(MODE_PRIVATE)
        val value =  getSharedPrefs.getString("message", "Nothing Yet")

        binding.textEditText.setText(value)

    }
}