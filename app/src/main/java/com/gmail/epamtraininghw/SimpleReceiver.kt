package com.gmail.epamtraininghw

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.TextView

class SimpleReceiver : BroadcastReceiver() {
    companion object {
        private const val MESSAGE = "message"
    }

    lateinit var messageTextView: TextView

    override fun onReceive(context: Context?, intent: Intent) {
        val activity: Activity = context as Activity
        messageTextView = activity.findViewById(R.id.incomingMessageTextView)

        if (intent.extras != null) {
            val message: String = intent.getStringExtra(MESSAGE)
            messageTextView.text = message
        } else {
            Log.e("TAG", "Empty bundle on BroadcastReceiver")
        }
    }
}
