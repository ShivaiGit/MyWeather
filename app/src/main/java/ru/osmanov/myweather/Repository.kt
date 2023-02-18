package ru.osmanov.myweather

interface Repository {
    fun getWeatherFromServer(): Weather
    fun getWeatherFromLocaleStorage(): Weather
}