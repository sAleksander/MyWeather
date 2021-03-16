package com.example.myweather.ui.elder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import com.example.myweather.R

class ElderFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_elder, container, false)

        val modeSwitch: ImageView = root.findViewById(R.id.elderAccessibilityButton)
        modeSwitch.setOnClickListener {
            view -> view.findNavController().navigate(R.id.action_elderFragment_to_normalFragment)
        }
        return root
    }
}