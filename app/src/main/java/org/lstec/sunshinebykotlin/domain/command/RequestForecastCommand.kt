package org.lstec.sunshinebykotlin.domain.command

import org.lstec.sunshinebykotlin.data.server.ForecastByZipCodeRequest
import org.lstec.sunshinebykotlin.domain.model.ForecastList
import org.lstec.sunshinebykotlin.data.server.ServerDataMapper
import org.lstec.sunshinebykotlin.domain.datasource.ForecastProvider

/**
 * Created by shaw on 13/08/2017.
 */
class RequestForecastCommand(private val zipCode: Long,
                             val forecastProvider: ForecastProvider = ForecastProvider()) : Commands<ForecastList> {

    companion object {
        val DAYS = 7
    }

    override fun execute(): ForecastList {
        return forecastProvider.requestByZipCode(zipCode, DAYS)
    }
}