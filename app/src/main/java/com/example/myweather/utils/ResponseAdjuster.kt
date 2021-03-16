package com.example.myweather.utils

import java.sql.Time

object ResponseAdjuster {
    fun KelvinToCelsius(degree:Double):Double{
        return Math.round((degree-(273.15))*10.0)/10.0
    }
    fun TimeFromNumber(number:Long):String{
        return Time(number).toString()
    }
    fun getIcon(rawIcon:String):String{
        return rawIcon.substring(0,2)
    }
}