package com.github.fcopardo.ui

import android.view.View

interface UI<T> {
    fun setData(data : T)
    fun asView() : View
}