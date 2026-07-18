package com.example.fragments

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fragments.databinding.ActivityCustomBroadcastInputBinding

class CustomBroadcastInputActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCustomBroadcastInputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomBroadcastInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSendBroadcast.setOnClickListener {
            val message = binding.etMessage.text.toString()
            val intent = Intent(this, CustomBroadcastReceiverActivity::class.java)
            intent.putExtra("MESSAGE", message)
            startActivity(intent)
        }
    }
}
