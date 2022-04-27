package com.smith.notificationdemo

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.android.material.snackbar.Snackbar
import com.smith.notificationdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val CHANNEL_ID = "com.smith.notificationdemo.mynotification"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val notificationId = 101
        val textTitle = "My Basic notification"
        val textContent = "By default, the notification's text content is truncated to fit one line. If you want your notification to be longer, you can enable an expandable notification by adding a style template with setStyle(). For example, the following code creates a larger text area:"


        // Create an explicit intent for an Activity in your app
        val intent = Intent(this, NotificationActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)


        var builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(textTitle)
            .setContentText(textContent)
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText(textContent)) // an expandable notification

            // Set the intent that will fire when the user taps the notification
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)


        // Create notification channel
        createNotificationChannel()

        binding.showNotificationButton.setOnClickListener {
            // Show notification
            with(NotificationManagerCompat.from(this)) {
                // notificationId is a unique int for each notification that you must define
                notify(notificationId, builder.build())
            }
        }


        // Show a snack bar
        binding.showSnackbarButton.setOnClickListener {
            showSnackBar()
        }


    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun showSnackBar() {

        val snackbar:Snackbar = Snackbar.make(
            findViewById(R.id.constraint_layout), // Best practice is to use a CoordinatorLayout
            "My snack bar text",
            Snackbar.LENGTH_INDEFINITE
        )
            .setAction("Display Toast", ShowToast())
            .setBackgroundTint(Color.parseColor("#FF03DAC5"))
            .setTextColor(Color.parseColor("#FF000000"))
            .setActionTextColor(Color.parseColor("#FFFFFF"))

        snackbar.show()

        // Do better than this. LOL!
        // Dismissing the LENGTH_INDEFINITE snackbar
        binding.root.setOnClickListener {
            snackbar.dismiss()
        }
    }
}