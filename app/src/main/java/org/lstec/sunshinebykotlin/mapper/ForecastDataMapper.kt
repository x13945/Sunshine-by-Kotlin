package org.lstec.sunshinebykotlin.mapper

import org.lstec.sunshinebykotlin.data.Forecast
import org.lstec.sunshinebykotlin.data.ForecastResult
import org.lstec.sunshinebykotlin.domain.model.ForecastList
import org.lstec.sunshinebykotlin.domain.model.Forecast as ModelForecast
import java.text.DateFormat
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by shaw on 13/08/2017.
 */
class ForecastDataMapper {
    fun convertFromDataModel(forecast: ForecastResult):ForecastList{
        return ForecastList(forecast.city.name, forecast.city.country, convertForecastListToDomain(forecast.list))
    }

    fun convertForecastListToDomain(forecastList: List<Forecast>): List<ModelForecast>{
        return forecastList.mapIndexed { i, forecast ->
            val dt = Calendar.getInstance().timeInMillis + TimeUnit.DAYS.toMillis(i.toLong())
            convertForecastItemToDomain(forecast.copy(dt = dt))
        }
    }

    fun convertForecastItemToDomain(forecast: Forecast): ModelForecast{
        return ModelForecast(convertDate(forecast.dt), forecast.weather[0].description,
                forecast.temp.max.toInt(), forecast.temp.min.toInt(), generateIconUrl(forecast.weather[0].icon))
    }

    private fun generateIconUrl(iconCode: String): String {
        return "http://openweathermap.org/img/w/$iconCode.png"
    }

    private fun convertDate(date: Long): String {
        var dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return dateFormat.format(date)
    }
}