package com.example.narasimhakonapalli_weatherapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ListForecastModel(
@SerializedName("list")
    val results:List<ForecastModel>
)
@Parcelize
data class ForecastModel(
    val clouds: CloudsX,
    val dt: Int,
    val dt_txt: String,
    val main: MainX,
    val pop: Double,
    val sys: SysX,
    val visibility: Int,
    val weather: List<WeatherX>,
    val wind: WindX
):Parcelable

@Parcelize
data class CloudsX(
    val all: Int
):Parcelable

@Parcelize
data class MainX(
    val feels_like: Double,
    val grnd_level: Int,
    val humidity: Int,
    val pressure: Int,
    val sea_level: Int,
    val temp: Double,
    val temp_kf: Double,
    val temp_max: Double,
    val temp_min: Double
):Parcelable

@Parcelize
data class SysX(
    val pod: String
):Parcelable

@Parcelize
data class WeatherX(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
):Parcelable

@Parcelize
data class WindX(
    val deg: Int,
    val gust: Double,
    val speed: Double
):Parcelable