package com.nigamar.androidassignment.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.nigamar.androidassignment.data.models.WeatherResponse
import com.nigamar.androidassignment.internals.API_KEY
import com.nigamar.androidassignment.internals.OPEN_WEATHER_URL
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("data/2.5/forecast")
    fun getWeatherDetails(@Query("lat")lat:Double,@Query("lon")lng:Double):Deferred<WeatherResponse>

    companion object {
        private var INSTANCE:WeatherApi ?=null
        fun getInstance():WeatherApi {
            val client = OkHttpClient.Builder().addInterceptor { chain: Interceptor.Chain ->
                val request= chain.request()
                val url= request.url().newBuilder().addQueryParameter("appid", API_KEY).build()
                val newRequest= request.newBuilder().url(url).build()
                // log for debug purpose to check the request that we are sending
                println(newRequest.toString())
                return@addInterceptor chain.proceed(newRequest)
            }.build()
            if (INSTANCE==null) INSTANCE = createInstance(client)
            return INSTANCE as WeatherApi
        }

        private fun createInstance(client:OkHttpClient):WeatherApi{
            val retrofit = Retrofit.Builder()
                .baseUrl(OPEN_WEATHER_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
            return retrofit.create(WeatherApi::class.java)
        }
    }
}