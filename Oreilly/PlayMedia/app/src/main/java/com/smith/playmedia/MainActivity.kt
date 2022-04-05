package com.smith.playmedia

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast
import com.smith.playmedia.databinding.ActivityMainBinding
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // From raw directory
//        mediaPlayer = MediaPlayer.create(this)

        // Streaming

        val url = "https://buildappswithpaulo.com/music/watch_me.mp3"
        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
        }

        try {
            mediaPlayer.setDataSource(url)
            mediaPlayer.prepareAsync()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        mediaPlayer.setOnCompletionListener { mp ->
            val duration = mp.duration / 1000   //to get duration in seconds
        }

        mediaPlayer.setOnPreparedListener { mp ->
            binding.seekBar.max = mp.duration

            binding.playButton.setOnClickListener {

                if (mp.isPlaying) {
                    //stop and give users the option to start again
                    mp.pause()
                    binding.playButton.setText(R.string.play_text)
                } else {
                    mp.start()
                    binding.playButton.setText(R.string.pause_text)
                }
            }

        }


        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {

                if (fromUser) {
                    mediaPlayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })

}



    override fun onDestroy() {
        super.onDestroy()
        if (mediaPlayer != null) {
            mediaPlayer.pause()
            mediaPlayer.release()
        }
    }
}