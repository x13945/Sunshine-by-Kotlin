package org.lstec.sunshinebykotlin.domain.commands

import org.lstec.sunshinebykotlin.domain.datasource.ForecastProvider
import org.lstec.sunshinebykotlin.domain.model.Forecast

/**
 * Created by shaw on 20/09/2017.
 */
class RequestDayForecastCommand(val id: Long,
                                private val forecastProvider: ForecastProvider = ForecastProvider()) : Command<Forecast>{
    override fun execute() = forecastProvider.requestForecast(id)
}