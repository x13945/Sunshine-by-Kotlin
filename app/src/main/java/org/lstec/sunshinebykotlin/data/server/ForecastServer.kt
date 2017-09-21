package org.lstec.sunshinebykotlin.data.server

import org.lstec.sunshinebykotlin.data.db.ForecastDb
import org.lstec.sunshinebykotlin.domain.datasource.ForecastDataSource
import org.lstec.sunshinebykotlin.domain.model.ForecastList

/**
 * Created by shaw on 13/09/2017.
 */

class ForecastServer(val dataMapper: ServerDataMapper = ServerDataMapper(),
                     val forecastDb: ForecastDb = ForecastDb()) : ForecastDataSource{
    override fun requestDayForecast(id: Long) = throw UnsupportedOperationException()

    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? {
        val result = ForecastByZipCodeRequest(zipCode).execute()
        val converted = dataMapper.convertToDomain(zipCode, result)
        forecastDb.saveForecast(converted)
        return forecastDb.requestForecastByZipCode(zipCode, date)
    }
}