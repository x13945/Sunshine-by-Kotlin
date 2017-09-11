package org.lstec.sunshinebykotlin.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import org.lstec.sunshinebykotlin.ui.adapters.ForecastListAdapter
import org.lstec.sunshinebykotlin.R
import org.lstec.sunshinebykotlin.domain.command.RequestForecastCommand

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        forecastList.layoutManager = LinearLayoutManager(this)

        doAsync {
            val result = RequestForecastCommand(94043).execute()
            uiThread {
                forecastList.adapter = ForecastListAdapter(result) { toast(it.description) }
            }
        }
    }
}

