package org.lstec.sunshinebykotlin.extensions

import java.text.DateFormat
import java.util.*

/**
 * Created by shaw on 21/09/2017.
 */

fun Long.toDateString(dateFormat: Int = DateFormat.MEDIUM): String {
    val df = DateFormat.getDateInstance(dateFormat, Locale.getDefault())
    return df.format(this)
}