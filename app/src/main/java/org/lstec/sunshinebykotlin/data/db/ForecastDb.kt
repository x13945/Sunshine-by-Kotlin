package org.lstec.sunshinebykotlin.data.db

import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.lstec.sunshinebykotlin.domain.datasource.ForecastDataSource
import org.lstec.sunshinebykotlin.domain.model.ForecastList
import org.lstec.sunshinebykotlin.extensions.*

/**
 * Created by shaw on 11/09/2017.
 */
class ForecastDb (val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance,
                  val dataMapper: DbDataMapper = DbDataMapper()) : ForecastDataSource{
    override fun requestDayForecast(id: Long) = forecastDbHelper.use {
        val forecast = select(DayForecastTable.NAME)
                .byId(id)
                .parseOpt{
                    DayForecast(HashMap(it))
                }
        forecast?.let {
            dataMapper.convertDayToDomain(forecast)
        }
    }

    override fun requestForecastByZipCode(zipCode: Long, date: Long) = forecastDbHelper.use {
        val dailyRequest = "${DayForecastTable.CITY_ID} = ? AND ${DayForecastTable.DATE} >= ?"
        val dailyForecast = select(DayForecastTable.NAME)
                .whereSimple(dailyRequest, zipCode.toString(), date.toString())
                .parseList{ DayForecast(HashMap(it))}

        val city = select(CityForecastTable.NAME)
                .whereSimple("${CityForecastTable.ID} = ?", zipCode.toString())
                .parseOpt { CityForecast(HashMap(it), dailyForecast) }

        city?.let {
            dataMapper.convertToDomain(city)
        }
    }

    fun saveForecast(forecast: ForecastList) = forecastDbHelper.use {
        clear(CityForecastTable.NAME)
        clear(DayForecastTable.NAME)

        with(dataMapper.convertFromDomain(forecast)){
            insert(CityForecastTable.NAME, *map.toVarargArray())
            dailyForecast.forEach{
                    insert(DayForecastTable.NAME, *it.map.toVarargArray())
            }
        }
    }
}