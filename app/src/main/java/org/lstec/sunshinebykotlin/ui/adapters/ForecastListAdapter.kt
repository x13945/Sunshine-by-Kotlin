package org.lstec.sunshinebykotlin.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_forecast.view.*
import org.lstec.sunshinebykotlin.R
import org.lstec.sunshinebykotlin.domain.model.Forecast
import org.lstec.sunshinebykotlin.domain.model.ForecastList
import org.lstec.sunshinebykotlin.extensions.ctx
import org.lstec.sunshinebykotlin.extensions.toDateString

/**
 * Created by shaw on 10/08/2017.
 */
class ForecastListAdapter(val weekForecast: ForecastList,
                          val itemClick: (Forecast) -> Unit) : RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.ctx).inflate(R.layout.item_forecast, parent, false)
        return ViewHolder(itemView, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(weekForecast[position])
    }

    override fun getItemCount() = weekForecast.size

    class ViewHolder(view: View,
                     val itemClick: (Forecast) -> Unit) : RecyclerView.ViewHolder(view){

        fun bindForecast(forecast: Forecast){
            with(forecast){
                Picasso.with(itemView.ctx)
                        .load(iconUrl)
                        .into(itemView.icon)
                itemView.date.text =  date.toDateString()
                itemView.description.text = description
                itemView.maxTemperature.text = "${high}º"
                itemView.minTemperature.text = "${low}º"
                itemView.setOnClickListener { itemClick(this) }
            }
        }

    }

}