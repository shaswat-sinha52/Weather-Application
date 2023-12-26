package com.example.weatherapplication

class Repo(
    private val retrofitServices:RetrofitServices
) {
    suspend fun getweatherdetails(city :String)=retrofitServices.getweatherdetails(city)


}