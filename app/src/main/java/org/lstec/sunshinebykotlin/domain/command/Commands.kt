package org.lstec.sunshinebykotlin.domain.command

/**
 * Created by shaw on 13/08/2017.
 */
interface Commands<out T> {
    fun execute():T
}