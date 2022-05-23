package com.example.narasimhakonapalli_weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.narasimhakonapalli_weatherapp.Communicator
import com.example.narasimhakonapalli_weatherapp.R
import com.example.narasimhakonapalli_weatherapp.views.CityFragment
import com.example.narasimhakonapalli_weatherapp.views.WeatherFragment

class MainActivity : AppCompatActivity() ,Communicator{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val cityFragment= CityFragment()
        supportFragmentManager.beginTransaction().replace(R.id.containerView,cityFragment).commit()
    }


    override fun passData(cityName: String, flag: Int) {
        val bundle=Bundle()
        bundle.putString("KEY",cityName)
        bundle.putInt("flag",flag)
        val transaction=this.supportFragmentManager.beginTransaction()
        val weatherFragment= WeatherFragment()
        weatherFragment.arguments=bundle
        transaction.replace(R.id.containerView,weatherFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}