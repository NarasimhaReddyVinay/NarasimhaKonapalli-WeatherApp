package com.example.narasimhakonapalli_weatherapp.views


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.narasimhakonapalli_weatherapp.repository.WeatherRepositoryImpl
import com.example.narasimhakonapalli_weatherapp.viewmodel.WeatherViewModel
import com.example.narasimhakonapalli_weatherapp.R
import com.example.narasimhakonapalli_weatherapp.model.ForecastModel
import com.example.narasimhakonapalli_weatherapp.databinding.FragmentWeatherBinding
import com.example.narasimhakonapalli_weatherapp.view.WeatherRecyclerAdapter


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class WeatherFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentWeatherBinding? = null
    private val binding: FragmentWeatherBinding get() = _binding!!



  private lateinit var weatherAdapter:WeatherRecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private val viewModel: WeatherViewModel by lazy {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return WeatherViewModel(WeatherRepositoryImpl()) as T
            }
        }.create(WeatherViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherBinding.inflate(layoutInflater)
         str=arguments?.getString("KEY")
        flag=arguments?.getInt("flag")

        configureObserver()

        binding.back.setOnClickListener { getFragmentManager()?.popBackStack() }
        return binding.root
    }

    private fun configureObserver() {
        weatherAdapter = WeatherRecyclerAdapter(openDetails = ::openDetails)
        viewModel.weatherReport.observe(viewLifecycleOwner) { response ->
            if (response.isEmpty()) {
                binding.tvErrorTextView.visibility=View.VISIBLE
                binding.tvErrorTextView.text = "Network failed"
                binding.pbLoading.visibility = View.GONE
            } else {
                weatherAdapter.setUserList(response)
                binding.cityNameFrag.text = str
                binding.apply {
                    rvList.layoutManager=LinearLayoutManager(requireContext())
                    rvList.adapter = weatherAdapter
                    pbLoading.visibility = View.GONE
                    tvErrorTextView.visibility = View.GONE
                }
            }
        }
    }

    private fun openDetails(weatherReport: ForecastModel) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.containerView, DetailedWeatherFragment.newInstance(weatherReport))
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object {
        var flag:Int?=null
        var str:String?=null

    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}