package com.nigamar.androidassignment.data.models

data class Country(
    val alpha2Code: String,
    val alpha3Code: String,
    val altSpellings: List<String>,
    val area: Double,
    val borders: List<String>,
    val callingCodes: List<String>,
    val capital: String,
    val currencies: List<String>,
    val demonym: String,
    val gini: Any,
    val languages: List<String>,
    val latlng: List<Double>,
    val name: String,
    val nativeName: String,
    val numericCode: String,
    val population: Int,
    val region: String,
    val relevance: String,
    val subregion: String,
    val timezones: List<String>,
    val topLevelDomain: List<String>,
    val translations: Translations
)

data class Translations(
    val de: String,
    val es: String,
    val fr: String,
    val `it`: String,
    val ja: String
)