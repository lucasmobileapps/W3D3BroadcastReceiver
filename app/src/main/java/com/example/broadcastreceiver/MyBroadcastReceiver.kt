package com.example.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import org.greenrobot.eventbus.EventBus

class MyBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val frag1 = intent?.getStringExtra("frag1")
        val frag2 = intent?.getStringExtra("frag2")
        val frag3 = intent?.getStringExtra("frag3")
        val isAirplaneModeOn = intent?.getBooleanExtra("state", false)
        val isTimeZoneChanged = intent?.getStringExtra("time-zone")
        val isPhoneOff = intent?.getBooleanExtra("EXTRA_SHUTDOWN_USERSPACE_ONLY", false)
        //val isAirplaneModeOn = intent.extras?.getBoolean("state")
        Log.d("TAG_HERE", "Airplane Mode is :$isAirplaneModeOn")
        Log.d("TAG_HERE", "Timezone changed is :$isTimeZoneChanged")
        Log.d("TAG_HERE", "Phone is Off :$isPhoneOff")

        EventBus.getDefault().post(MyMessage(frag1, frag2, frag3)) ?: ""
        Log.d("TAG_HERE", "Messages :$frag1 $frag2 $frag3")

        //context?.startActivity(Intent(context, MainActivity::class.java))

    }

}