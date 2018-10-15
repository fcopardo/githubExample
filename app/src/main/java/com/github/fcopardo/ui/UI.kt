package com.github.fcopardo.ui

import android.view.View

/**
 * Represents a piece of UI
 */
interface UI<T> {
    fun setData(data : T)
    fun asView() : View
}