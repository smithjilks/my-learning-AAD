package com.smith.frameanimation

import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.animation.AnimationUtils
import com.smith.frameanimation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var batAnimation: AnimationDrawable

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.batId.setBackgroundResource(R.drawable.bat_anim)
//        batAnimation = binding.batId.background as AnimationDrawable

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
//        batAnimation.start()

        val handler = Handler()
        handler.postDelayed(
            Runnable {
                val startAnimation =
                    AnimationUtils.loadAnimation(applicationContext, R.anim.fadein_animation)
                binding.batId.startAnimation(startAnimation)
//                     batAnimation.stop()
            }, 50)
        return super.onTouchEvent(event)

    }
}