package ru.osmanov.myweather.repository

interface Repository {
    fun getWeatherFromServer(): Weather
    fun getWeatherFromLocaleStorageRus(): List<Weather>
    fun getWeatherFromLocaleStorageDag(): List<Weather>
}