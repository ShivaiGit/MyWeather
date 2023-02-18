package ru.osmanov.myweather.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.osmanov.myweather.AppState
import java.lang.Thread.sleep

class MainViewModel(private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()) :
    ViewModel() {

    fun getLiveData() = liveDataToObserve
    fun getWeather() = getDataFromLocalSource()

    //Имитирует запрос к БД или ещё какому-то источнику данных в приложении
    private fun getDataFromLocalSource() {
        liveDataToObserve.value = AppState.Loading // меняем состояние приложения на "Идет загрузка"
        Thread {
            sleep(1000)
            liveDataToObserve.postValue(AppState.Success(Any())) //загрузка успешна
        }.start()
    }

}