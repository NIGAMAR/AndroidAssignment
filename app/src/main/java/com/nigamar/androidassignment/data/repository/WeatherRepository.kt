package com.nigamar.androidassignment.data.repository

import com.nigamar.androidassignment.data.models.Country
import com.nigamar.androidassignment.data.models.WeatherModel

interface WeatherRepository {
    fun getAllCountries():ArrayList<Country>
    fun getCountryDetails(name:String)
    fun getCurrentWeather(lat:Double,lng:Double):WeatherModel
    fun getFutureWeather(lat:Double,lng: Double):WeatherModel
}