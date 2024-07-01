package com.example.stableserviceexample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class BootReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            Toast.makeText(context, "Boot completed, starting service", Toast.LENGTH_SHORT).show()
            val serviceIntent = Intent(context, MyForegroundService::class.java)
            context.startForegroundService(serviceIntent)
            ServiceChecker.scheduleServiceCheck(context)
        }
    }
}