package com.github.kmachida12345.myapplication

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context

class NotificationChannelGenerator {
    private val id = System.currentTimeMillis().toString()
    fun createNotificationChannel(pattern: LongArray, context: Context) {

        val channel = NotificationChannel(id, id, NotificationManager.IMPORTANCE_HIGH).apply {
            description = "fuga"
            lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            vibrationPattern = pattern
            enableVibration(true)
            enableLights(true)
            setShowBadge(true)
        }

        (context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).createNotificationChannels(
            arrayListOf(channel))
    }
    fun sendNotification(context: Context) {
        val notification: Notification = Notification.Builder(context, id)
            .setContentTitle("hoge")
            .setContentText("hoge")
            .setSmallIcon(android.R.drawable.ic_dialog_email)
            .build()
        (context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).notify(0xbeef, notification)
    }
}