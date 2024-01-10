package com.example.notifications

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat

class MyNotificationManager(
    private val context: Context
) {
    private val notificationManager
            by lazy { context.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager }

    fun getNotify(id: Int, title: String, message: String) {
            val notification = createNotification(title, message)
            notificationManager.notify(id, notification)
    }

    private fun createNotification(title: String, message: String): Notification {
        createNotificationChannel()
        val defaultRingtone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val pendingIntent = createPendingIntent()

        return NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setColor(Color.GREEN)
            .setContentText(message)
            .setAutoCancel(true)
            .setSound(defaultRingtone)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .build()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.getNotificationChannel(NOTIFICATION_CHANNEL_ID)
                ?: notificationManager.createNotificationChannel(
                    NotificationChannel(
                        NOTIFICATION_CHANNEL_ID,
                        NOTIFICATION_CHANNEL_NAME,
                        NotificationManager.IMPORTANCE_HIGH
                    )
                )
        }
    }

    private fun createPendingIntent(): PendingIntent {
        val intent = Intent(context, MainActivity::class.java)

        return PendingIntent.getActivity(
            context,
            NOTIFICATION_REQUEST_CODE,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )
    }

    companion object {
        private const val NOTIFICATION_CHANNEL_ID = "com.example.notifications"
        private const val NOTIFICATION_CHANNEL_NAME = "notifications"
        private const val NOTIFICATION_REQUEST_CODE = 0
    }
}