package com.example.narasimhakonapalli_weatherapp.Api


import com.example.narasimhakonapalli_weatherapp.model.ListForecastModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val KEY = "d67f35fcb3c6299094dcebd90ed58eff"

interface ApiService {

    @GET("data/2.5/forecast?appid=d67f35fcb3c6299094dcebd90ed58eff")
    suspend fun getWeatherReportForecast(
        @Query("q") cityName: String?
    ): Response<ListForecastModel>

    companion object {
        private var instance: ApiService? = null
        fun getApiService(): ApiService {
            if (instance == null) {
                instance = Retrofit.Builder()
                    .baseUrl("https://api.openweathermap.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiService::class.java)
            }
            return instance!!
        }
    }

}
