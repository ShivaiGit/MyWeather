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
    fun getWeatherFromLocaleSourceRus() = getDataFromLocalSource(isRussian = true)
    fun getWeatherFromLocaleSourceDag() = getDataFromLocalSource(isRussian = false)
    fun getWeatherFromRemoteSource() = getDataFromLocalSource(isRussian = true)

    //Имитирует запрос к БД или ещё какому-то источнику данных в приложении
    private fun getDataFromLocalSource(isRussian: Boolean) {
        liveDataToObserve.value = AppState.Loading
        Thread {
            sleep(1000) //имитируем процесс загрузки в приложении
            liveDataToObserve.postValue(
                AppState.Success(
                    if (isRussian) repositoryImpl.getWeatherFromLocaleStorageRus()
                    else repositoryImpl.getWeatherFromLocaleStorageDag()
                )
            )
        }.start()
    }
}