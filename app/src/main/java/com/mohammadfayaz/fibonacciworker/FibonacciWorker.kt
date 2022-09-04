package com.mohammadfayaz.fibonacciworker

import android.content.Context
import android.os.Environment
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileWriter
import java.io.InputStream
import java.math.BigInteger

class FibonacciWorker(appContext: Context, workerParams: WorkerParameters):
       CoroutineWorker(appContext, workerParams) {
   override suspend fun doWork(): Result {

     Log.d("worker", "work started")

     val count = inputData.getInt("count", 99)
     val outputFile = File(
       applicationContext.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)?.path!! + "/",
       "result.txt"
     )

     Log.d("worker", outputFile.path)

     var ba: BigInteger = BigInteger.ZERO
     var bb: BigInteger = BigInteger.ONE
     var bc: BigInteger = ba.add(bb)

     withContext(Dispatchers.IO) {

       val fw = FileWriter(
         applicationContext.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)?.path!!
             + "/result.txt",
         true)
       fw.append("$ba, $bb, ")

       for (i in 0..count) {
         bc = ba.add(bb)
         ba = bb
         bb = bc

         fw.append("$bc, ")
       }

     }

     Log.d("worker", "work ended")
     return Result.success()
   }
}

//fun File.copyInputStreamToFile(inputStream: InputStream) {
//  this.outputStream().use { fileOut ->
//    inputStream.copyTo(fileOut)
//  }
//}
