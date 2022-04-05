package com.example.background.workers

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.background.OUTPUT_PATH
import java.io.File

private const val TAG = "CleanupWorker"

class CleanupWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    /**
     * Makes a notification when the work starts.
     * Slows down the work so that
     * it's easier to see each WorkRequest start, even on emulated devices
    */
    override fun doWork(): Result {
        makeStatusNotification("Cleaning up temp files", applicationContext)
        sleep()

        return try {
            val outputDirectory = File(applicationContext.filesDir, OUTPUT_PATH)
            if (outputDirectory.exists()) {
                val entries = outputDirectory.listFiles()

                if (entries != null) {

                    for (entry in entries) {
                        val name = entry.name

                        if (name.isNotEmpty() && name.endsWith(".png")) {
                            val deleted = entry.delete()
                            Log.i(TAG, "Deleted $name - $deleted")
                        }
                    }
                }
            }

            Result.success()
        } catch (exception: Exception) {
            exception.printStackTrace()
            Result.failure()
        }
    }

}