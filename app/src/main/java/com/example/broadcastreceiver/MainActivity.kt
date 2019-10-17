package com.example.broadcastreceiver

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment1.*
import kotlinx.android.synthetic.main.fragment2.*
import kotlinx.android.synthetic.main.fragment3.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class MainActivity : AppCompatActivity() {

    private val fragment1 = Fragment1()
    private val fragment2 = Fragment2()
    private val fragment3 = Fragment3()
    val myBRC = MyBroadcastReceiver()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        EventBus.getDefault().register(this)
        registerReceiver(myBRC, IntentFilter("broadcast"))
        registerReceiver(myBRC, IntentFilter("android.intent.action.AIRPLANE_MODE"))
        registerReceiver(myBRC, IntentFilter("android.intent.action.TIMEZONE_CHANGED"))
        registerReceiver(myBRC, IntentFilter("android.intent.action.ACTION_SHUTDOWN"))
        registerReceiver(myBRC, IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"))

        supportFragmentManager.beginTransaction()
            .add(R.id.frag1_framelayout, fragment1)
            .commit()
        supportFragmentManager.beginTransaction()
            .add(R.id.frag2_framelayout, fragment2)
            .commit()
        supportFragmentManager.beginTransaction()
            .add(R.id.frag3_framelayout, fragment3)
            .commit()

        Broadcast_button.setOnClickListener {
            sendBroadcast(Intent("broadcast").also { myIntent ->
                /*
                myIntent.putExtra("frag1", "Hi Fragment 1")
                myIntent.putExtra("frag2", "Hi Fragment 2")
                myIntent.putExtra("frag3", "Hi Fragment 3")

                 */

                val extras = Bundle()
                extras.putString("frag1", "Hi Fragment 1")
                extras.putString("frag2", "Hi Fragment 2")
                extras.putString("frag3", "Hi Fragment 3")
                myIntent.putExtras(extras)


            })
        }

    }

    @Subscribe
    fun onEvent(message: MyMessage) {
        frag1_textview.text = message.messagefragment1
        frag2_textview.text = message.messagefragment2
        frag3_textview.text = message.messagefragment3
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)

        unregisterReceiver(myBRC)


    }
}
