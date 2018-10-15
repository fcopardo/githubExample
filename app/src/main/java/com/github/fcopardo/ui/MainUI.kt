package com.github.fcopardo.ui

interface MainUI<T> : UI<T> {
    fun shouldLeave() : Boolean
    fun switchContents()
}