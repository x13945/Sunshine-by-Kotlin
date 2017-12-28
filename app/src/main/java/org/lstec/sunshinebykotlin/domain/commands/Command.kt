package org.lstec.sunshinebykotlin.domain.commands

/**
 * Created by shaw on 13/08/2017.
 */
interface Command<out T> {
    fun execute():T
}