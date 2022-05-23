package com.example.narasimhakonapalli_weatherapp.repository


import com.example.narasimhakonapalli_weatherapp.Api.ApiService
import com.example.narasimhakonapalli_weatherapp.model.*


interface WeatherRepository {
    suspend fun getWeatherReportForecast(cityName: String?): ListForecastModel

}

class WeatherRepositoryImpl(private val service: ApiService = ApiService.getApiService()): WeatherRepository
{
   override suspend fun getWeatherReportForecast(cityName: String?): ListForecastModel {
        val response=service.getWeatherReportForecast(cityName = cityName)
        return if(response.isSuccessful){
            response.body()!!
        }else{
            ListForecastModel(emptyList())
        }
    }




}
