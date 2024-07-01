package com.example.stableserviceexample

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat


class MyForegroundService: Service() {
    private val TAG = "MyForegroundService"
    private val CHANNEL_ID = "MyServiceChannel"
    private val NOTIFICATION_ID = 1
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    companion object {
        var running = false
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        running = true
        handler = Handler()
        runnable = object : Runnable {
            override fun run() {
                // Your periodic task
                Log.d(TAG, "Task executed")

                // Schedule the task to run again in 1 minute
                handler.postDelayed(this, 5000)
            }
        }
        startForegroundService()
        handler.post(runnable)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    private fun startForegroundService() {
        createNotificationChannel()
        val notification: Notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("My Foreground Service")
            .setContentText("Service is running")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()
        startForeground(NOTIFICATION_ID, notification)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Foreground Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable)
        running = false
    }
}