package com.smith.notificationdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.smith.notificationdemo.databinding.ActivityMainBinding
import com.smith.notificationdemo.databinding.ActivityNotificationBinding

class NotificationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNotificationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}