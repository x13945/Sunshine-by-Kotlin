package org.lstec.sunshinebykotlin.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread
import org.lstec.sunshinebykotlin.R
import org.lstec.sunshinebykotlin.domain.commands.RequestDayForecastCommand
import org.lstec.sunshinebykotlin.domain.model.Forecast
import org.lstec.sunshinebykotlin.extensions.color
import org.lstec.sunshinebykotlin.extensions.textColor
import org.lstec.sunshinebykotlin.extensions.toDateString
import java.text.DateFormat

class DetailActivity : AppCompatActivity(), ToolbarManager {

    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    companion object {
        val ID = "DetailActivity:id"
        val CITY_NAME = "DetailActivity:cityName"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initToolbar()

        toolbarTitle = intent.getStringExtra(CITY_NAME)
        enableHomeAsUp {
            onBackPressed()
        }
        doAsync {
            val result = RequestDayForecastCommand(intent.getLongExtra(ID, -1)).execute()
            uiThread {
                bindForecast(result)
            }
        }
    }

    private fun bindForecast(forecast: Forecast) = with(forecast) {
        Picasso.with(ctx).load(iconUrl).into(icon)
        toolbar.subtitle = date.toDateString(DateFormat.FULL)
        weatherDescription.text = description
        bindWeather(high to maxTemperature, low to minTemperature)
    }

    private fun bindWeather(vararg views: Pair<Int, TextView>) = views.forEach {
        it.second.text = "${it.first}"
        it.second.textColor = color(when(it.first) {
            in -50..0 -> android.R.color.holo_red_dark
            in 0..50 -> android.R.color.holo_orange_dark
            else ->  android.R.color.holo_green_dark
        })
    }
}
