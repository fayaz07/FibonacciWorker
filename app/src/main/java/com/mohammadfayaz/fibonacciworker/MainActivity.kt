package com.mohammadfayaz.fibonacciworker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewbinding.ViewBinding
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.mohammadfayaz.fibonacciworker.databinding.ActivityMainBinding
import java.util.concurrent.Executor

class MainActivity : AppCompatActivity() {

  lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.startWork.setOnClickListener {
      setupWorker()
    }
  }

  private fun setupWorker() {
    val data = Data.Builder()
    data.putInt("count", 50000)

    val fibonacciWorkerRequest: WorkRequest =
      OneTimeWorkRequestBuilder<FibonacciWorker>()
        .setInputData(data.build())
        .build()

    WorkManager
      .getInstance(this)
      .enqueue(fibonacciWorkerRequest)

  }
}