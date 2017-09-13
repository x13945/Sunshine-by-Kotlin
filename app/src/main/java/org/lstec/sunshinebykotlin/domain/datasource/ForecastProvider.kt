package org.lstec.sunshinebykotlin.domain.datasource

import org.lstec.sunshinebykotlin.data.db.ForecastDb
import org.lstec.sunshinebykotlin.data.server.ForecastServer
import org.lstec.sunshinebykotlin.domain.model.Forecast
import org.lstec.sunshinebykotlin.domain.model.ForecastList
import org.lstec.sunshinebykotlin.extensions.firstResult

/**
 * Created by shaw on 13/09/2017.
 */
class ForecastProvider (val sources: List<ForecastDataSource> = ForecastProvider.SOURCES){
    companion object {
        val DAY_IN_MILLIS = 1000 * 60 * 60 * 24
        val SOURCES = listOf(ForecastDb(), ForecastServer())
    }

    fun requestByZipCode(zipCode: Long, days: Int): ForecastList
    = sources.firstResult { requestSource(it, days, zipCode) }

    private fun requestSource(source: ForecastDataSource, days: Int, zipCode: Long): ForecastList? {
        val res = source.requestForecastByZipCode(zipCode, todayTimeSpon())
        if (res != null && res.size >= days) {
            return res
        } else {
            return null
        }
    }

    private fun todayTimeSpon(): Long {
        return System.currentTimeMillis() / DAY_IN_MILLIS * DAY_IN_MILLIS
    }
}