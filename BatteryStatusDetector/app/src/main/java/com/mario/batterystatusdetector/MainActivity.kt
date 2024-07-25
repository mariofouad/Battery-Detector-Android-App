package com.mario.batterystatusdetector

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mario.batterystatusdetector.ui.theme.BatteryStatusDetectorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BatteryStatusDetectorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BatteryStatusDetector()
                }
            }
        }
    }
}


@Composable
fun BatteryStatusDetector(){
    val filter = IntentFilter().apply {
        val filter = IntentFilter().apply {
            addAction(Intent.ACTION_BATTERY_LOW)
            addAction(Intent.ACTION_BATTERY_OKAY)
        }

        var batteryImage by rememberSaveable{
            mutableIntStateOf(R.drawable.battery_full)
        }

        val batteryReceiver = BatteryReceiver(){
            batteryImage = if(it == 0)
                R.drawable.battery_low
            else
                R.drawable.battery_full
        }

        LocalContext.current.registerReceiver(batteryReceiver, filter)

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = batteryImage),
                contentDescription = null,
                modifier = Modifier.size(128.dp)
            )
        }

    }}
