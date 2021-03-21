package com.example.myweather.ui.normal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.myweather.R
import com.example.myweather.ui.MainViewModel

class NormalFragment : Fragment() {
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_normal, container, false)
        val searchButton: ImageView = root.findViewById(R.id.normalSearchButton)
        val cityInput: TextView = root.findViewById(R.id.normalCityInput)
        val accessibilityButton: ImageView = root.findViewById(R.id.normalAccessibilityButton)
        val pressureDisplay: TextView = root.findViewById(R.id.normalPressureDisplay)
        val temperatureDisplay: TextView = root.findViewById(R.id.normalTemperatureDisplay)
        val visualisationDisplay: ImageView = root.findViewById(R.id.normalVisualisationDisplay)
        val descriptionDisplay: TextView = root.findViewById(R.id.normalDescriptionDisplay)
        val dawnDisplay: TextView = root.findViewById(R.id.normalDawnDisplay)
        val duskDisplay: TextView = root.findViewById(R.id.normalDuskDisplay)
        val timeDisplay: TextView = root.findViewById(R.id.normalTimeDisplay)
        val dateDisplay: TextView = root.findViewById(R.id.normalDateDisplay)


        accessibilityButton.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_normalFragment_to_elderFragment)
        }

        mainViewModel.Pressure.observe(viewLifecycleOwner, Observer {
            pressureDisplay.text = "${it.toString()}hPa"
        })
        mainViewModel.Temperature.observe(viewLifecycleOwner, Observer {
            temperatureDisplay.text = "${it.toString()}°"
        })
        mainViewModel.Description.observe(viewLifecycleOwner, Observer {
            descriptionDisplay.text = it.toString()
        })
        mainViewModel.Dawn.observe(viewLifecycleOwner, Observer {
            dawnDisplay.text = it.toString()
        })
        mainViewModel.Dusk.observe(viewLifecycleOwner, Observer {
            duskDisplay.text = it.toString()
        })
        mainViewModel.Date.observe(viewLifecycleOwner, Observer {
            dateDisplay.text = it
        })
        mainViewModel.Visualisation.observe(viewLifecycleOwner, Observer {
            when (it) {
                "01" -> visualisationDisplay.setImageResource(R.drawable.ic_weather_clear_sky)
                "02" -> visualisationDisplay.setImageResource(R.drawable.ic_weather_few_clouds)
                "03" -> visualisationDisplay.setImageResource(R.drawable.ic_weather_scattered_clouds)
                "04" -> visualisationDisplay.setImageResource(R.drawable.ic_weather_broken_clouds)
                "09" -> visualisationDisplay.setImageResource(R.drawable.ic_weather_shower_rain)
                "10" -> visualisationDisplay.setImageResource(R.drawable.ic_weather_rain)
                "11" -> visualisationDisplay.setImageResource(R.drawable.ic_weather_thunderstorm)
                "13" -> visualisationDisplay.setImageResource(R.drawable.ic_weather_snow)
                "50" -> visualisationDisplay.setImageResource(R.drawable.ic_weather_mist)
                "404" -> visualisationDisplay.setImageResource(R.drawable.ic_weather_attention)
                else -> visualisationDisplay.setImageResource(0)
            }
        })
        searchButton.setOnClickListener {
                mainViewModel.getForecast(cityInput.text.toString())
        }

        return root
    }
}