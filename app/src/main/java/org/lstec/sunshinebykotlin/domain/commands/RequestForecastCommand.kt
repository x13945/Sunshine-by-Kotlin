package org.lstec.sunshinebykotlin.domain.commands

import org.lstec.sunshinebykotlin.domain.model.ForecastList
import org.lstec.sunshinebykotlin.domain.datasource.ForecastProvider

/**
 * Created by shaw on 13/08/2017.
 */
class RequestForecastCommand(private val zipCode: Long,
                             val forecastProvider: ForecastProvider = ForecastProvider()) : Command<ForecastList> {

    companion object {
        val DAYS = 7
    }

    override fun execute() = forecastProvider.requestByZipCode(zipCode, DAYS)
}