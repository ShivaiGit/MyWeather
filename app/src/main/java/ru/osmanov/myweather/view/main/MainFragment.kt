package ru.osmanov.myweather.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import ru.osmanov.myweather.R
import ru.osmanov.myweather.databinding.FragmentMainBinding
import ru.osmanov.myweather.repository.Weather
import ru.osmanov.myweather.viewmodel.AppState
import ru.osmanov.myweather.viewmodel.MainViewModel

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel
    private val adapter = MainFragmentAdapter()
    private var isDataSetRus: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mainFragmentRecyclerView.adapter = adapter
        binding.mainFragmentFAB.setOnClickListener { changeWeatherDataSet() }
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer {
            renderData(it)
        })
        viewModel.getWeatherFromLocaleSourceRus()

    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                adapter.setWeather(appState.weatherData)
            }
            is AppState.Loading -> {
                //не стал визуально имитировать загрузку
            }
            is AppState.Error -> {
                Snackbar
                    .make(binding.mainFragmentFAB, "Ошибка загрузки", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Перезапуск") { viewModel.getWeatherFromLocaleSourceRus() }
                    .show()
            }
        }

    }

    private fun changeWeatherDataSet() {
        if (isDataSetRus) {
            viewModel.getWeatherFromLocaleSourceRus()
            binding.mainFragmentFAB.setImageResource(R.drawable.russia)
        } else {
            viewModel.getWeatherFromLocaleSourceDag()
            binding.mainFragmentFAB.setImageResource(R.drawable.dagestan)
        }
        isDataSetRus = !isDataSetRus
    }

    companion object {
        fun newInstance() = MainFragment()
    }

    // интерфейс, чтобы передавать данные между адаптером списка и фрагментом
    interface OnItemViewClickListener {
        fun onItemViewClick(weather: Weather)
    }


}