package com.smith.soundpoolexample

import android.media.AudioAttributes
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.smith.soundpoolexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var soundPool: SoundPool

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val audioAttributes = AudioAttributes.Builder()
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
            .build()

        soundPool = SoundPool.Builder()
            .setMaxStreams(4) //we have 4 sounds
            .setAudioAttributes(audioAttributes)
            .build()

        val soundOne = soundPool.load(this, R.raw.complete, 1)
        val soundTwo = soundPool.load(this, R.raw.correct, 1)
        val soundThree = soundPool.load(this, R.raw.defeat_one, 1)
        val soundFour = soundPool.load(this, R.raw.defeat_two, 1)

        binding.button.setOnClickListener {
            soundPool.play(soundOne, 1F, 1F, 0, 0, 1F)
        }

        binding.buttonTwo.setOnClickListener {
            soundPool.play(soundTwo, 1F, 1F, 0, 0, 1F)
        }

        binding.buttonThree.setOnClickListener {
            soundPool.play(soundThree, 1F, 1F, 0, 0, 1F)
        }

        binding.buttonFour.setOnClickListener {
            soundPool.play(soundFour, 1F, 1F, 0, 0, 1F)
        }

//        binding.root.setOnClickListener {
//            Toast.makeText(this, it.id.toString(), Toast.LENGTH_LONG).show()
//            val sound = when(it.id) {
//                R.id.button -> soundOne
//                R.id.button_two -> soundTwo
//                R.id.button_three -> soundThree
//                R.id.button_four -> soundFour
//                else -> 0
//            }
//            soundPool.play(sound, 1F, 1F, 0, 0, 1F)
//        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (soundPool != null) {
            soundPool.release()
        }
    }
}