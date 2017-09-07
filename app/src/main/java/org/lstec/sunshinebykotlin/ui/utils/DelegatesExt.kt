package org.lstec.sunshinebykotlin.ui.utils

import kotlin.reflect.KProperty

/**
 * Created by shaw on 07/09/2017.
 */

object DelegatesExt{
    fun <T> notNullSingleValue() = NotNullSingleValueVar<T>()
}

class NotNullSingleValueVar<T> {
    private var value: T? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T{
        return value ?:throw IllegalStateException("${property.name} not initialized")
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T){
        if (this.value == null) {
            this.value = value
        } else {
            throw IllegalStateException("${property.name} already initialized")
        }
    }
}