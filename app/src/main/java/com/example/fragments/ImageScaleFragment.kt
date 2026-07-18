package com.example.fragments

import android.graphics.Matrix
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ScaleGestureDetector
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.fragments.databinding.FragmentImageScaleBinding

class ImageScaleFragment : Fragment() {

    private var _binding: FragmentImageScaleBinding? = null
    private val binding get() = _binding!!

    private var scaleFactor = 1.0f
    private val matrix = Matrix()
    private lateinit var scaleGestureDetector: ScaleGestureDetector

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImageScaleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageUrl = "https://picsum.photos/800/800"
        Glide.with(this).load(imageUrl).into(binding.imageView)

        scaleGestureDetector = ScaleGestureDetector(requireContext(), object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
            override fun onScale(detector: ScaleGestureDetector): Boolean {
                scaleFactor *= detector.scaleFactor
                scaleFactor = scaleFactor.coerceIn(0.1f, 10.0f)
                
                matrix.setScale(scaleFactor, scaleFactor, detector.focusX, detector.focusY)
                binding.imageView.imageMatrix = matrix
                return true
            }
        })

        binding.imageView.setOnTouchListener { v, event ->
            scaleGestureDetector.onTouchEvent(event)
            true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
