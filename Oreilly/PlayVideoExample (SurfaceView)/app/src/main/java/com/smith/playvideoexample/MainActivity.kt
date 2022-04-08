package com.smith.playvideoexample

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.smith.playvideoexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), SurfaceHolder.Callback {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mediaPlayer = MediaPlayer.create(this, R.raw.outro_music)
        binding.surfaceView.keepScreenOn = true

        val holder = binding.surfaceView.holder
        holder.addCallback(this)
        holder.setFixedSize(400, 300)

        binding.buttonPlay.setOnClickListener {
            mediaPlayer.start()
        }

        binding.buttonPause.setOnClickListener {
            mediaPlayer.pause()
        }

        binding.buttonSkip.setOnClickListener {
            mediaPlayer.seekTo(mediaPlayer.duration / 2)
        }

    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        mediaPlayer.setDisplay(holder)
        mediaPlayer.start()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {
    }

    override fun onPause() {
        super.onPause()
        if (mediaPlayer != null){
            mediaPlayer.pause()
            mediaPlayer.release()
        }
    }
}