package org.lstec.sunshinebykotlin.domain.datasource

import org.lstec.sunshinebykotlin.domain.model.Forecast
import org.lstec.sunshinebykotlin.domain.model.ForecastList

/**
 * Created by shaw on 13/09/2017.
 */
interface ForecastDataSource {
    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList?

    fun requestDayForecast(id: Long): Forecast?
}