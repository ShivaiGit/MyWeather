package ru.osmanov.myweather.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import ru.osmanov.myweather.R

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val observer = Observer<Any> { renderData(it) } //выполняет метод renderData, как только LiveData обновляет данные, которые она хранит
        viewModel.getData().observe(viewLifecycleOwner, observer)
        // если Данные, которые хранит LiveData, изменятся, Observer сразу об этом узнает и вызовет метод renderData, куда передаст новые данные.
    }

    private fun renderData(data: Any?) { //В качестве аргумента renderData принимает объект, возвращаемый LiveData
        Toast.makeText(context, "data", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

}