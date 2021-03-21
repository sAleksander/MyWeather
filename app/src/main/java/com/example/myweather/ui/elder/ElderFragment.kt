package com.example.myweather.ui.elder

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

class ElderFragment : Fragment() {
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_elder, container, false)
        val searchButton: ImageView = root.findViewById(R.id.elderSearchButton)
        val cityInput: TextView = root.findViewById(R.id.elderCityInput)
        val accessibilityButton: ImageView = root.findViewById(R.id.elderAccessibilityButton)
        val pressureDisplay: TextView = root.findViewById(R.id.elderPressureDisplay)
        val temperatureDisplay: TextView = root.findViewById(R.id.elderTemperatureDisplay)
        val visualisationDisplay: ImageView = root.findViewById(R.id.elderVisualisationDisplay)
        val descriptionDisplay: TextView = root.findViewById(R.id.elderDescriptionDisplay)
        val dawnDisplay: TextView = root.findViewById(R.id.elderDawnDisplay)
        val duskDisplay: TextView = root.findViewById(R.id.elderDuskDisplay)
        val timeDisplay: TextView = root.findViewById(R.id.elderTimeDisplay)
        val dateDisplay: TextView = root.findViewById(R.id.elderDateDisplay)


        accessibilityButton.setOnClickListener {
            view -> view.findNavController().navigate(R.id.action_elderFragment_to_normalFragment)
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
                "01" -> visualisationDisplay.setImageResource(R.drawable.ic_weather_clear_sky_black)
                "02" -> visualisationDisplay.setImageResource(R.drawable.ic_weather_few_clouds_black)
                "03" -> visualisationDisplay.setImageResource(R.drawable.ic_weather_scattered_clouds_black)
                "04" -> visualisationDisplay.setImageResource(R.drawable.ic_weather_broken_clouds_black)
                "09" -> visualisationDisplay.setImageResource(R.drawable.ic_weather_shower_rain_black)
                "10" -> visualisationDisplay.setImageResource(R.drawable.ic_weather_rain_black)
                "11" -> visualisationDisplay.setImageResource(R.drawable.ic_weather_thunderstorm_black)
                "13" -> visualisationDisplay.setImageResource(R.drawable.ic_weather_snow_black)
                "50" -> visualisationDisplay.setImageResource(R.drawable.ic_weather_mist_black)
                "404" -> visualisationDisplay.setImageResource(R.drawable.ic_weather_attention_black)
                else -> visualisationDisplay.setImageResource(0)
            }
        })
        searchButton.setOnClickListener {
            mainViewModel.getForecast(cityInput.text.toString())
        }
        return root
    }
}