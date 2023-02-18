package ru.osmanov.myweather.viewmodel

import ru.osmanov.myweather.repository.Weather

sealed class AppState {
    data class Success(val weatherData: Weather) : AppState() //данные получены
    data class Error(val error: Throwable) : AppState() //произошла ошибка
    object Loading : AppState() //идет загрузка данных
}
