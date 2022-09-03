package com.mohammadfayaz.fibonacciworker

import android.content.Context
import android.os.Environment
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.io.File
import java.io.InputStream

class FibonacciWorker(appContext: Context, workerParams: WorkerParameters):
       Worker(appContext, workerParams) {
   override fun doWork(): Result {

     Log.d("worker", "work started")

     val count = inputData.getInt("count", 99)
     val outputFile = File(
       applicationContext.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)?.path!! + "/",
       "result.txt"
     )

     Log.d("worker", outputFile.path)

     val stringBuffer: StringBuffer = StringBuffer("")

     var a = 0
     var b = 1
     var c = a + b

     stringBuffer.append(a).append(", ").append(b).append(", ")

     for (i in 0..count) {
        c = a + b
       stringBuffer.append(c).append(", ")
       a = b
       b = c
     }
     outputFile.copyInputStreamToFile(stringBuffer.toString().byteInputStream())
     Log.d("worker", "work ended")
     return Result.success()
   }
}

fun File.copyInputStreamToFile(inputStream: InputStream) {
  this.outputStream().use { fileOut ->
    inputStream.copyTo(fileOut)
  }
}
