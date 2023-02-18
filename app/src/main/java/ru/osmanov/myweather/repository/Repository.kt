package ru.osmanov.myweather.repository

interface Repository {
    fun getWeatherFromServer(): Weather
    fun getWeatherFromLocaleStorage(): Weather
}