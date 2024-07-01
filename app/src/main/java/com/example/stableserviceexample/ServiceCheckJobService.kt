package com.example.stableserviceexample

import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent

class ServiceCheckJobService: JobService() {
    override fun onStartJob(p0: JobParameters?): Boolean {
        if (!isServiceRunning()) {
            val serviceIntent = Intent(this, MyForegroundService::class.java)
            startForegroundService(serviceIntent)
        }
        return true
    }

    override fun onStopJob(p0: JobParameters?): Boolean {
        return true
    }

    private fun isServiceRunning(): Boolean {
        return MyForegroundService.running
    }
}