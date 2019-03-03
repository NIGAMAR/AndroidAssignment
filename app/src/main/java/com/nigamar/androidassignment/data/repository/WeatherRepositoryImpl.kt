package com.nigamar.androidassignment.data.repository

import com.nigamar.androidassignment.data.models.Country
import com.nigamar.androidassignment.data.models.WeatherModel

class WeatherRepositoryImpl : WeatherRepository {
    override fun getAllCountries(): ArrayList<Country> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCountryDetails(name: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCurrentWeather(lat: Double, lng: Double): WeatherModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFutureWeather(lat: Double, lng: Double): WeatherModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}