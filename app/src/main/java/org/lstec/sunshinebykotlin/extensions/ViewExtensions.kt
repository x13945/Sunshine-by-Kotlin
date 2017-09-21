package org.lstec.sunshinebykotlin.extensions

import android.content.Context
import android.view.View
import android.widget.TextView

/**
 * Created by shaw on 14/08/2017.
 */

val View.ctx:Context
    get() = context

var TextView.textColor: Int
    get() = currentTextColor
    set(value) = setTextColor(value)