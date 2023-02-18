package ru.osmanov.myweather

sealed class AppState {
    data class Success(val weatherData: Any) : AppState()//данные получены
    data class Error(val error: Throwable) : AppState()//произошла ошибка
    object Loading : AppState() // идет загрузка данных
}
