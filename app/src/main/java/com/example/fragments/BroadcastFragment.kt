package com.example.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.fragments.databinding.FragmentBroadcastBinding

class BroadcastFragment : Fragment() {

    private var _binding: FragmentBroadcastBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBroadcastBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val options = arrayOf("Custom broadcast receiver", "System battery notification receiver")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, options)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerBroadcastType.adapter = adapter

        binding.btnProceed.setOnClickListener {
            val selection = binding.spinnerBroadcastType.selectedItemPosition
            if (selection == 0) {
                val intent = Intent(requireContext(), CustomBroadcastInputActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(requireContext(), BatteryNotificationActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
