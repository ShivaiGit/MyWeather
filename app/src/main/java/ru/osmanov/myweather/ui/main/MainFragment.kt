package ru.osmanov.myweather.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import ru.osmanov.myweather.AppState
import ru.osmanov.myweather.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    companion object {
        fun newInstance() = MainFragment()
    }
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel

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

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner) { renderData(it) }
    // если Данные, которые хранит LiveData, изменятся, Observer сразу об этом узнает и вызовет метод renderData, куда передаст новые данные
        viewModel.getWeather()
    }

    private fun renderData(appState: AppState) { //В качестве аргумента renderData принимает объект, возвращаемый LiveData
        when (appState) {
            is AppState.Success -> {
                val weatherData = appState.weatherData
                binding.loadingLayout.visibility = View.GONE
                Snackbar.make(binding.mainView, "Успешно", Snackbar.LENGTH_LONG).show()
            }
            //если идет загрузка отображается иконка загрузки
            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                Snackbar
                    .make(binding.mainView, "Ошибка загрузки", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Перезапуск") { viewModel.getWeather() }
                    .show()
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}