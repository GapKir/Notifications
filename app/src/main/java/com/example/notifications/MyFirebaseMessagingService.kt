package com.example.notifications


import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService: FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        MyNotificationManager(this)
            .getNotify(
                NOTIFICATION_ID,
                message.notification?.title ?: NOTIFICATION_DEFAULT_TITLE,
                message.notification?.body ?: NOTIFICATION_DEFAULT_MESSAGE
            )
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(LOG_TAG, "${MyFirebaseMessagingService::class.java.simpleName} --token $token")
    }

    companion object{
        private const val NOTIFICATION_ID = 2
        private const val NOTIFICATION_DEFAULT_TITLE = "Title from Firebase"
        private const val NOTIFICATION_DEFAULT_MESSAGE = "Message from Firebase"
        private const val LOG_TAG = "LOG_TAG"
    }
}