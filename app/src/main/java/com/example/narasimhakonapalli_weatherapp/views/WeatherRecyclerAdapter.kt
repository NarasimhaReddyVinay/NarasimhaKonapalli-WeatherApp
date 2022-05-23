package com.example.narasimhakonapalli_weatherapp.view

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.narasimhakonapalli_weatherapp.databinding.WeatherListItemBinding
import com.example.narasimhakonapalli_weatherapp.model.ForecastModel
import com.example.narasimhakonapalli_weatherapp.views.DetailedWeatherFragment
import com.example.narasimhakonapalli_weatherapp.views.WeatherFragment
import java.time.format.DateTimeFormatter


class WeatherRecyclerAdapter(
    private val userList: MutableList<ForecastModel> = mutableListOf(),
    private val openDetails: (ForecastModel) -> Unit
    ): RecyclerView.Adapter<WeatherRecyclerAdapter.UserViewHolder>() {

    fun setUserList(newList: List<ForecastModel>) {
        userList.clear()
        userList.addAll(newList)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(
        private val binding: WeatherListItemBinding
        ): RecyclerView.ViewHolder(binding.root) {

            @SuppressLint("SetTextI18n")
            fun bind(forecastModel: ForecastModel) {


                when(WeatherFragment.flag){

                    0 -> binding.tvCurrentTemp.text= forecastModel.main.temp.toString()+"K"
                    1 -> binding.tvCurrentTemp.text= DetailedWeatherFragment.kelvinToCelsius(forecastModel.main.temp).toString().substring(0,4)+"C"
                    2 -> binding.tvCurrentTemp.text= DetailedWeatherFragment.kelvinToFahrenheit(forecastModel.main.temp).toString().substring(0,4)+"F"

                }


                binding.tvTimeDesc.text=forecastModel.dt_txt.toString()
                binding.root.setOnClickListener {
                    openDetails(forecastModel)

                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UserViewHolder(
            WeatherListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount() = userList.size
}