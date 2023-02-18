package ru.osmanov.myweather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.osmanov.myweather.repository.Repository
import ru.osmanov.myweather.repository.RepositoryImpl
import java.lang.Thread.sleep

class MainViewModel(
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryImpl: Repository = RepositoryImpl()
) :
    ViewModel() {

    fun getLiveData() = liveDataToObserve
    fun getWeatherFromLocaleSource() = getDataFromLocalSource()
    fun getWeatherFromRemoteSource() = getDataFromLocalSource()

    //Имитирует запрос к БД или ещё какому-то источнику данных в приложении
    private fun getDataFromLocalSource() {
        liveDataToObserve.value = AppState.Loading // меняем состояние приложения на "Идет загрузка"
        Thread {
            sleep(1000)
            liveDataToObserve.postValue(AppState.Success(repositoryImpl.getWeatherFromLocaleStorage()))
        }.start()
    }

}