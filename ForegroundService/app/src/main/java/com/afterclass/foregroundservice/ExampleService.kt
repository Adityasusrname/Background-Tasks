package com.afterclass.foregroundservice

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.afterclass.foregroundservice.App.Companion.CHANNEL_ID

class ExampleService: Service() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val input = intent?.getStringExtra("inputExtra")
        val notificationIntent = Intent(this,MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this,0,notificationIntent,0)
        val notification = NotificationCompat.Builder(this,CHANNEL_ID).setContentTitle("Example Service")
            .setContentText(input).setSmallIcon(R.drawable.ic_android).setContentIntent(pendingIntent).build()
        startForeground(1,notification)
        return START_NOT_STICKY


    }
}