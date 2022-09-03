package com.mohammadfayaz.fibonacciworker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class FibonacciWorker(appContext: Context, workerParams: WorkerParameters):
       Worker(appContext, workerParams) {
   override fun doWork(): Result {
       return Result.success()
   }
}
