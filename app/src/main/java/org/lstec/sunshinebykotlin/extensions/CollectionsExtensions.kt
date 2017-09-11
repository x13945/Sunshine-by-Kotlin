package org.lstec.sunshinebykotlin.extensions

/**
 * Created by shaw on 11/09/2017.
 */

fun <K, V : Any> Map<K, V?>.toVarargArray(): Array<out Pair<K, V>> =
        map({ Pair(it.key, it.value!!)}).toTypedArray()

