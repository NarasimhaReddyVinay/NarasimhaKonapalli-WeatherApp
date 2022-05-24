package com.example.narasimhakonapalli_weatherapp.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import com.example.narasimhakonapalli_weatherapp.Communicator
import com.example.narasimhakonapalli_weatherapp.databinding.FragmentLocationBinding


private const val CityName = "param1"
private const val ARG_PARAM2 = "param2"


class CityFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentLocationBinding? = null
    private val binding: FragmentLocationBinding get() = _binding!!
    private lateinit var communicator: Communicator
    lateinit var radioButton: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(CityName)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLocationBinding.inflate(layoutInflater)
        communicator = activity as Communicator


        binding.btnSearch.setOnClickListener {
            var flag=0
            if( binding.rbKelvin.isChecked){
                flag=0
            }else if(binding.rbCelsius.isChecked){
                flag=1
            }else if(binding.rbFahrenheit.isChecked){
                flag=2
            }
            if(binding.editText.text.toString().isEmpty()){
                Toast.makeText(requireContext(),"Please enter the city Name",Toast.LENGTH_SHORT).show()
            }else {
                communicator.passData(binding.editText.text.toString(), flag)
            }

        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}