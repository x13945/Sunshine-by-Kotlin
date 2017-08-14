package org.lstec.sunshinebykotlin.data

data class ForecastResult(
        val city: City,
        val cnt: Int,
        val cod: String,
        val message: Double,
        val list: List<Forecast>
)

data class Coordinates(
        val lon: Double,
        val lat: Double
)

data class City(
        val country: String,
        val coord: Coordinates,
        val name: String,
        val id: Int,
        val population: Int
)

data class Temperature(
        val min: Double,
        val max: Double,
        val eve: Double,
        val night: Double,
        val day: Double,
        val morn: Double
)

data class Weather(
        val icon: String,
        val description: String,
        val main: String,
        val id: Int
)

data class Forecast(
        val dt: Long,
        val temp: Temperature,
        val deg: Int,
        val weather: List<Weather>,
        val humidity: Int,
        val pressure: Double,
        val clouds: Int,
        val speed: Double
)