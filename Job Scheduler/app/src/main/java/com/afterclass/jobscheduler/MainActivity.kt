package com.afterclass.jobscheduler

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {
    companion object{
        val TAG = "Main Activity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    @RequiresApi(Build.VERSION_CODES.P)
    fun scheduleJob(view: View){
    val componentName=ComponentName(this,ExampleJobService::class.java)
        val jobInfo = JobInfo.Builder(123,componentName)
           // .setRequiresCharging(true)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_CELLULAR)
            .setPersisted(true)
            .setPeriodic(15*60*1000)
            .build()
        val scheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
        val resultCode = scheduler.schedule(jobInfo)
        if(resultCode == JobScheduler.RESULT_SUCCESS){
            Log.d(TAG,"Job Scheduled!")
        }
        else{
            Log.d(TAG,"Job Scheduling failed!")
        }
    }
    fun cancelJob(view:View){
          val scheduler=getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
        scheduler.cancel(123)
        Log.d(TAG,"Job Canceled!")
    }
}