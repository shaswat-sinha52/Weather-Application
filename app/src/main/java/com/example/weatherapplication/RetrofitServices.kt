package com.example.weatherapplication

import com.example.weatherapplication.Datamodel.WeatherResponsedatamodel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServices {
    @GET("v1/current.json?key=dec1cf3fc9a64b1cba685827232412")
    suspend fun getweatherdetails(
        @Query("q") city:String
    ):Response<WeatherResponsedatamodel>
}