package org.lstec.sunshinebykotlin.ui

import android.app.Application
import org.lstec.sunshinebykotlin.extensions.DelegatesExt

/**
 * Created by shaw on 07/09/2017.
 */

class App : Application() {
    companion object {
        var instance: App by DelegatesExt.notNullSingleValue()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}