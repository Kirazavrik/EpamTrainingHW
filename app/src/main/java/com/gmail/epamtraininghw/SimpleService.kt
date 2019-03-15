package com.gmail.epamtraininghw

import android.app.IntentService
import android.content.Intent
import android.util.Log

class SimpleService : IntentService("Simple service") {
    companion object {
        private const val MESSAGE = "Simple Message"
        private const val TAG = "Error"
    }

    private var broadcastIntent = Intent("com.gmail.wr30mg.MESSAGE")

    override fun onHandleIntent(intent: Intent?) {
        try {
            Thread.sleep(3000)
        } catch (e: InterruptedException) {
            Log.e(TAG, "There was an error while perform Thread.sleep $e")
            Thread.currentThread().interrupt()
        }

        broadcastIntent.putExtra("message", MESSAGE)
        sendBroadcast(broadcastIntent)

    }
}