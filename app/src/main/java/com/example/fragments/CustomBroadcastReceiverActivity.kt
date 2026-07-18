package com.example.fragments

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fragments.databinding.ActivityCustomBroadcastReceiverBinding

class CustomBroadcastReceiverActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCustomBroadcastReceiverBinding
    private lateinit var receiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomBroadcastReceiverBinding.inflate(layoutInflater)
        setContentView(binding.root)

        receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val message = intent?.getStringExtra("EXTRA_MESSAGE")
                binding.tvReceivedMessage.text = "Receiver caught: $message"
            }
        }

        val messageFromIntent = intent.getStringExtra("MESSAGE")
        binding.tvReceivedMessage.text = "Waiting for broadcast..."

        binding.btnTriggerBroadcast.setOnClickListener {
            val broadcastIntent = Intent("com.example.fragments.CUSTOM_BROADCAST")
            broadcastIntent.putExtra("EXTRA_MESSAGE", messageFromIntent)
            broadcastIntent.setPackage(packageName)
            sendBroadcast(broadcastIntent)
        }
    }

    override fun onStart() {
        super.onStart()
        val filter = IntentFilter("com.example.fragments.CUSTOM_BROADCAST")
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(receiver, filter, RECEIVER_NOT_EXPORTED)
        } else {
            registerReceiver(receiver, filter)
        }
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(receiver)
    }
}
