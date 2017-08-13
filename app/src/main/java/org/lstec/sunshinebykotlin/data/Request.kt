package org.lstec.sunshinebykotlin.data

import android.util.Log
import java.net.URL

/**
 * Created by shaw on 13/08/2017.
 */
class Request(val url: String) {
    fun run(){
        val forecastJsonStr = URL(url).readText()
        Log.d(javaClass.simpleName, forecastJsonStr)
    }
}