package com.github.fcopardo.ui

/**
 * Represents a window with two possible content sets, like a master/detail
 */
interface MainUI<T> : UI<T> {
    fun shouldLeave() : Boolean
    fun switchContents()
}