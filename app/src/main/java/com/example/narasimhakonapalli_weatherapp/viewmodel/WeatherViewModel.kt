package com.example.narasimhakonapalli_weatherapp.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.narasimhakonapalli_weatherapp.repository.WeatherRepositoryImpl
import com.example.narasimhakonapalli_weatherapp.model.*
import com.example.narasimhakonapalli_weatherapp.views.WeatherFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class WeatherViewModel(private val repositoryImpl: WeatherRepositoryImpl) : ViewModel() {

    init {
        getWeatherReport(WeatherFragment.str)
    }
    private val _weatherReport = MutableLiveData<List<ForecastModel>>()


    val weatherReport: LiveData<List<ForecastModel> >get() = _weatherReport


    fun getWeatherReport(cityName:String?) {
       CoroutineScope(Dispatchers.Main).launch {
            val response = repositoryImpl.getWeatherReportForecast(cityName)
            _weatherReport.postValue(response.results)

        }
    }


}