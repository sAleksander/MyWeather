package com.example.myweather.ui.normal

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myweather.network.OpenWeatherApi
import com.example.myweather.utils.ResponseAdjuster
import kotlinx.coroutines.launch
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class NormalViewModel: ViewModel() {
    private val _pressure = MutableLiveData<String>()
    val Pressure: LiveData<String> = _pressure
    private val _temperature = MutableLiveData<String>()
    val Temperature: LiveData<String> = _temperature
    private val _visualisation = MutableLiveData<String>()
    val Visualisation: LiveData<String> = _visualisation
    private val _description = MutableLiveData<String>()
    val Description: LiveData<String> = _description
    private val _dawn = MutableLiveData<String>()
    val Dawn: LiveData<String> = _dawn
    private val _dusk = MutableLiveData<String>()
    val Dusk: LiveData<String> = _dusk
    private val _date = MutableLiveData<String>()
    val Date: LiveData<String> = _date

    fun getForecast(city:String = "Warsaw"){
        viewModelScope.launch {
            try {
            val response = OpenWeatherApi.API.getCurrentWeather(city).await()
            _pressure.value = response.main.pressure.toString()
            _temperature.value = ResponseAdjuster.KelvinToCelsius(response.main.temp).toString()
            _description.value = response.weather[0].description
            _dawn.value = ResponseAdjuster.TimeFromNumber(response.sys.sunrise.toLong())
            _dusk.value = ResponseAdjuster.TimeFromNumber(response.sys.sunset.toLong())
            _visualisation.value = ResponseAdjuster.getIcon(response.weather[0].icon)
            } catch (t: Throwable){
                val errorAmount = "NaN!"
                val errorMessage = "No data!"
                _pressure.value = errorAmount
                _temperature.value = errorAmount
                _description.value = errorMessage
                _dawn.value = errorAmount
                _dusk.value = errorAmount
                _visualisation.value = "404"
            }
        }
    }

    init {
        getForecast()
        _date.value = LocalDate.now().toString()
    }
}