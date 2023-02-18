package ru.osmanov.myweather

data class Weather(val city: City = getDefaultCity(), val temperature: Int = 0, val feelsLike: Int = 0)

fun getDefaultCity() = City ("Дербент", 42.057669, 48.288776)
