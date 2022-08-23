package com.afterclass.jobscheduler

import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log

class ExampleJobService:JobService() {
    companion object{
        val TAG="ExampleJobService"
        var jobCancelled = false
    }
    override fun onStartJob(params: JobParameters?): Boolean {
        Log.d(TAG,"Job Started!")
        doBackgroundWork(params)
        return true //If job is not finished yet,some asynchronous work is going on!
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        Log.d(TAG,"Job cancelled before completion!")
        jobCancelled=true
        return true
        //return 'true' if you want to reschedule
    }

    fun doBackgroundWork(params: JobParameters?){
        Thread(Runnable {
            var i =0
            while(i<4){
                Log.d(TAG,"run:${i}")
                if(jobCancelled){
                    return@Runnable
                }
                Thread.sleep(1000)
                i++
            }
            Log.d(TAG,"Job Finished")
            jobFinished(params,false)
        }).start()
    }
}