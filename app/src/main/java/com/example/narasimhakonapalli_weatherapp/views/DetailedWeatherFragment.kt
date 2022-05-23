package com.example.narasimhakonapalli_weatherapp.views


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.narasimhakonapalli_weatherapp.databinding.FragmentDetailedWeatherBinding
import com.example.narasimhakonapalli_weatherapp.model.ForecastModel



private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class DetailedWeatherFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentDetailedWeatherBinding? = null
    private val binding: FragmentDetailedWeatherBinding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDetailedWeatherBinding.inflate(layoutInflater)

        val forecastModel: ForecastModel? = arguments?.getParcelable(ARG_PARAM1)

       val icon: String = forecastModel?.weather?.get(0)?.icon!!

        binding.imageView.setOnClickListener { getFragmentManager()?.popBackStack() }

        binding.detailedDesc.text = forecastModel.weather[0].description
        binding.humidity.text = forecastModel.main.humidity.toString() + "%"
        binding.pressure.text = forecastModel.main.pressure.toString() + "hPa"
        binding.wind.text = forecastModel.wind.speed.toString() + "Km/h"
        binding.detailcityname.text = WeatherFragment.str


        when (WeatherFragment.flag) {
            0 -> {
                binding.tvDetailedTemp.text = forecastModel.main.temp.toString() + "K"
                binding.feelsLike.text = forecastModel.main.feels_like.toString() + "K"

            }
            1 -> {
                binding.tvDetailedTemp.text =
                    forecastModel.main.temp.let { kelvinToCelsius(it) }.toString()
                        .substring(0, 4) + "C"
                binding.feelsLike.text =
                    forecastModel.main.feels_like.let { kelvinToCelsius(it) }.toString()
                        .substring(0, 4) + "C"

            }

            2 -> {
                binding.tvDetailedTemp.text =
                    forecastModel.main.temp.let { kelvinToFahrenheit(it) }.toString()
                        .substring(0, 4) + "F"
                binding.feelsLike.text =
                    forecastModel.main.feels_like.let { kelvinToFahrenheit(it) }.toString()
                        .substring(0, 4) + "F"

            }

        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(weatherReport: ForecastModel) =
            DetailedWeatherFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1, weatherReport)


                }
            }

        fun kelvinToCelsius(temp: Double): Double {
            return temp - 273.15
        }

        fun kelvinToFahrenheit(temp: Double): Double {
            return 1.8 * (temp - 273) + 32
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}