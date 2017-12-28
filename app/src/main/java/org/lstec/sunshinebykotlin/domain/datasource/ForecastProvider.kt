package org.lstec.sunshinebykotlin.domain.datasource

import org.lstec.sunshinebykotlin.data.db.ForecastDb
import org.lstec.sunshinebykotlin.data.server.ForecastServer
import org.lstec.sunshinebykotlin.domain.model.Forecast
import org.lstec.sunshinebykotlin.extensions.firstResult

/**
 * Created by shaw on 13/09/2017.
 */
class ForecastProvider (val sources: List<ForecastDataSource> = ForecastProvider.SOURCES){
    companion object {
        val DAY_IN_MILLIS = 1000 * 60 * 60 * 24
        val SOURCES = listOf(ForecastDb(), ForecastServer())
    }

    fun requestForecast(id: Long): Forecast = requestToSources {
        it.requestDayForecast(id)
    }

    fun requestByZipCode(zipCode: Long, days: Int) = requestToSources {
        val res = it.requestForecastByZipCode(zipCode, todayTimeSpan())
        if (res != null && res.size >= days) {
            res
        } else {
            null
        }
    }

    private fun <T : Any> requestToSources(f: (ForecastDataSource) -> T?): T = sources.firstResult {
        f(it)
    }

    private fun todayTimeSpan(): Long {
        return System.currentTimeMillis() / DAY_IN_MILLIS * DAY_IN_MILLIS
    }
}