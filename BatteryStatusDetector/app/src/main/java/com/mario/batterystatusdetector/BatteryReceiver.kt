package com.mario.batterystatusdetector

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BatteryReceiver(val batteryStatus : (Int) -> Unit): BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        if(intent!!.action == Intent.ACTION_BATTERY_LOW)
            batteryStatus(0);
        else
            batteryStatus(1);
    }

}