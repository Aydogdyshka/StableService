package com.example.stableserviceexample

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context


object ServiceChecker {
    private const val JOB_ID = 123

    fun scheduleServiceCheck(context: Context) {
        val componentName = ComponentName(context, ServiceCheckJobService::class.java)
        val jobInfo = JobInfo.Builder(JOB_ID, componentName)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
            .setPeriodic(15 * 60 * 1000) // Every 15 minutes
            .build()

        val jobScheduler = context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        jobScheduler.schedule(jobInfo)
    }
}