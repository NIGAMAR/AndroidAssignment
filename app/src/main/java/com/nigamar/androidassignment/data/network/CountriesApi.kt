package com.nigamar.androidassignment.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.nigamar.androidassignment.data.models.Country
import com.nigamar.androidassignment.internals.API_KEY
import com.nigamar.androidassignment.internals.COUNTRIES_BASE_URL
import com.nigamar.androidassignment.internals.OPEN_WEATHER_URL
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface CountriesApi {

    @GET("rest/v1/all")
    fun getAllCountries(): Deferred<List<Country>>

    companion object {
        private var INSTANCE:CountriesApi ?=null
        fun getInstance():CountriesApi {
            val client = OkHttpClient.Builder().build()
            if (INSTANCE==null) INSTANCE = createInstance(client)
            return INSTANCE as CountriesApi
        }

        private fun createInstance(client: OkHttpClient):CountriesApi{
            val retrofit = Retrofit.Builder()
                .baseUrl(COUNTRIES_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
            return retrofit.create(CountriesApi::class.java)
        }
    }
}