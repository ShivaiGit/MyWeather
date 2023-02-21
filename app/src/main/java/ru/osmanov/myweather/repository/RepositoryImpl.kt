package ru.osmanov.myweather.repository

class RepositoryImpl: Repository {
    override fun getWeatherFromServer(): Weather {
        return Weather()
    }

    override fun getWeatherFromLocaleStorageRus(): List<Weather> {
        return getRussianCities()
    }
    override fun getWeatherFromLocaleStorageDag(): List<Weather> {
        return getCitiesDagestan()
    }

}