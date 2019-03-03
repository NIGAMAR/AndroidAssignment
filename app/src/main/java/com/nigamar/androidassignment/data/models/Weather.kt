package com.nigamar.androidassignment.data.models
import com.google.gson.annotations.SerializedName


data class WeatherResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<Detail>,
    val message: Double
)

data class City(
    val coord: Coord,
    val country: String,
    val id: Int,
    val name: String
)

data class Coord(
    val lat: Double,
    val lon: Double
)

data class Detail(
    val clouds: Clouds,
    val dt: Int,
    @SerializedName("dt_txt")
    val dtTxt: String,
    val main: Main,
    val rain: Rain,
    val sys: Sys,
    val weather: List<WeatherDetail>,
    val wind: Wind
)

data class Main(
    @SerializedName("grnd_level")
    val grndLevel: Double,
    val humidity: Int,
    val pressure: Double,
    @SerializedName("sea_level")
    val seaLevel: Double,
    val temp: Double,
    @SerializedName("temp_kf")
    val tempKf: Int,
    @SerializedName("temp_max")
    val tempMax: Double,
    @SerializedName("temp_min")
    val tempMin: Double
)

data class Sys(
    val pod: String
)

data class WeatherDetail(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)

data class Clouds(
    val all: Int
)

data class Wind(
    val deg: Double,
    val speed: Double
)

class Rain(
)