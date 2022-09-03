package com.mohammadfayaz.fibonacciworker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import java.util.concurrent.Executor

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    setupWorker()
  }

  private fun setupWorker() {
    val data = Data.Builder()
    data.putInt("count", 99)

    val fibonacciWorkerRequest: WorkRequest =
      OneTimeWorkRequestBuilder<FibonacciWorker>()
        .setInputData(data.build())
        .build()

    WorkManager
      .getInstance(this)
      .enqueue(fibonacciWorkerRequest)

  }
}