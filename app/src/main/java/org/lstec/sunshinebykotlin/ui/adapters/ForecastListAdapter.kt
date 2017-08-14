package org.lstec.sunshinebykotlin.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import org.lstec.sunshinebykotlin.R
import org.lstec.sunshinebykotlin.domain.model.Forecast
import org.lstec.sunshinebykotlin.domain.model.ForecastList
import org.lstec.sunshinebykotlin.ui.utils.ctx
import org.jetbrains.anko.find

/**
 * Created by shaw on 10/08/2017.
 */
class ForecastListAdapter(val weekForecast: ForecastList,
                          val itemClick: OnItemClickListener) : RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.ctx).inflate(R.layout.item_forecast, parent, false)
        return ViewHolder(itemView, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(weekForecast[position])
    }

    override fun getItemCount(): Int {
        return weekForecast.size()
    }

    class ViewHolder(view: View,
                     val itemClick: OnItemClickListener) : RecyclerView.ViewHolder(view){
        private val iconView = view.find<ImageView>(R.id.icon)
        private val dateView = view.find<TextView>(R.id.date)
        private val descriptionView = view.find<TextView>(R.id.description)
        private val maxTemperature = view.find<TextView>(R.id.maxTemperature)
        private val minTemperature = view.find<TextView>(R.id.minTemperature)

        fun bindForecast(forecast: Forecast){
            with(forecast){
                Picasso.with(itemView.ctx)
                        .load(iconUrl)
                        .into(iconView)
                dateView.text = date
                descriptionView.text = description
                maxTemperature.text = "${high}º"
                minTemperature.text = "${low}º"
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }

    interface OnItemClickListener{
        operator fun invoke(forecast: Forecast)
    }
}