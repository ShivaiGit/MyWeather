package ru.osmanov.myweather.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ru.osmanov.myweather.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val observer =
            Observer<Any> { renderData(it) } //выполняет метод renderData, как только LiveData обновляет данные которые она хранит
        viewModel.getLiveData().observe(
            viewLifecycleOwner,
            observer
        ) // если Данные, которые хранит LiveData, изменятся, Observer сразу об этом узнает и вызовет метод renderData, куда передаст новые данные

    }

    private fun renderData(data: Any) { //В качестве аргумента renderData принимает объект, возвращаемый LiveData
        Toast.makeText(context, "data", Toast.LENGTH_SHORT).show()
    }

    private lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

}