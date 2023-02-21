package ru.osmanov.myweather.repository

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Weather(
    val city: City = getDefaultCity(),
    val temperature: Int = 0,
    val feelsLike: Int = 0
) : Parcelable

fun getDefaultCity() = City("Дербент", 42.057669, 48.288776)

fun getRussianCities(): List<Weather> {
    return listOf(
        Weather(City("Москва", 55.75, 37.62), 1, 2),
        Weather(City("Анапа", 44.89, 37.32), 22, 19),
        Weather(City("Рязань", 54.6269, 39.6916), 10, 8),
        Weather(City("Санкт-Петербург", 59.9386, 30.3141), 5, 2),
        Weather(City("Новосибирск", 55.0415, 82.9346), -8, -7),
        Weather(City("Нижний Новгород", 56.3287, 44.002), 11, 9),
        Weather(City("Махачкала", 42.98, 47.5), 10, 12),
        Weather(City("Ростов-на-Дону", 47.2313, 39.7233), 3, 2),

        )
}

fun getCitiesDagestan(): List<Weather> {
    return listOf(
        Weather(City("Махачкала", 42.98, 47.5), 10, 12),
        Weather(City("Дербент", 42.07, 48.29), 12, 14),
        Weather(City("Кизляр", 43.85, 46.71), 10, 8),
        Weather(City("Буйнакск", 42.82, 47.12), 5, 2),
        Weather(City("Каспийск", 42.88, 47.64), 8, 10),
        Weather(City("Хасавюрт", 43.25, 46.59), 11, 9),
    )
}


