package com.example.notifications

import android.Manifest
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.notifications.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val requestPermission = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) sendNotificationFromActivity()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnShowNotification.setOnClickListener {
            checkNotificationPermission()
        }
    }

    private fun checkNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermission.launch(Manifest.permission.POST_NOTIFICATIONS)
        } else {
            sendNotificationFromActivity()
        }
    }

    private fun sendNotificationFromActivity() {
            MyNotificationManager(this)
                .getNotify(
                    NOTIFICATION_ID,
                    NOTIFICATION_TITLE,
                    NOTIFICATION_MESSAGE
                )
        }

    companion object {
        private const val NOTIFICATION_ID = 1
        private const val NOTIFICATION_TITLE = "Title from Activity"
        private const val NOTIFICATION_MESSAGE = "Message from Activity"
    }
}