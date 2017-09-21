package org.lstec.sunshinebykotlin.extensions

import android.content.Context
import android.support.v4.content.ContextCompat

/**
 * Created by shaw on 20/09/2017.
 */

fun Context.color(res: Int): Int {
    return ContextCompat.getColor(this, res)
}