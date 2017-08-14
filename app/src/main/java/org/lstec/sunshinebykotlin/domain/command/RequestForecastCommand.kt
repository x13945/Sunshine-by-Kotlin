package org.lstec.sunshinebykotlin.domain.command

import org.lstec.sunshinebykotlin.data.ForecastRequest
import org.lstec.sunshinebykotlin.domain.model.ForecastList
import org.lstec.sunshinebykotlin.mapper.ForecastDataMapper

/**
 * Created by shaw on 13/08/2017.
 */
class RequestForecastCommand(val zipCode: String) : Commands<ForecastList> {
    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(forecastRequest.execuate())
    }
}