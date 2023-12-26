package com.example.weatherapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapplication.Datamodel.WeatherResponsedatamodel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val repo: Repo
):ViewModel() {

    var weatherdetaillivedata= MutableLiveData<WeatherResponsedatamodel>()
    val  isloading=MutableLiveData<Boolean>(false)

    fun getweatherdetails(city:String){
        viewModelScope.launch(Dispatchers.IO) {
            isloading.postValue(true)
            val response=repo.getweatherdetails(city)
            if (response.isSuccessful){
                weatherdetaillivedata.postValue(response.body())
                isloading.postValue(false)
            }
        }
    }
}