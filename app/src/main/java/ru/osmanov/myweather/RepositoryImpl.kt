package ru.osmanov.myweather

class RepositoryImpl: Repository {
    override fun getWeatherFromServer(): Weather {
        return Weather()
    }

    override fun getWeatherFromLocaleStorage(): Weather {
        return Weather()
    }
}