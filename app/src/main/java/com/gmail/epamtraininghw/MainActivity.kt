package com.gmail.epamtraininghw

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        const val FRAGMENT_NAME = "SimpleFragment"
        const val INTENT_FILTER_ACTION = "com.gmail.wr30mg.MESSAGE"
    }

    private val filter = IntentFilter()
    private lateinit var serviceIntent: Intent
    private val broadcastReceiver: BroadcastReceiver = SimpleReceiver()
    private var simpleFragment: Fragment = SimpleFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            simpleFragment = supportFragmentManager.getFragment(savedInstanceState, FRAGMENT_NAME)!!
        }

        startFragment()

        serviceIntent = Intent(this, SimpleService::class.java)

        startServiceButton.setOnClickListener {
            startService(serviceIntent)
        }

        stopServiceButton.setOnClickListener {
            stopService(serviceIntent)
        }
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)

        supportFragmentManager.putFragment(outState!!, FRAGMENT_NAME, simpleFragment)
    }

    override fun onResume() {
        super.onResume()

        filter.addAction(INTENT_FILTER_ACTION)
        registerReceiver(broadcastReceiver, filter)
    }

    override fun onPause() {
        super.onPause()

        unregisterReceiver(broadcastReceiver)
    }

    private fun startFragment() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentView, simpleFragment)
            .commit()
    }

}
