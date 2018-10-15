package com.github.fcopardo.model.ui

import java.util.*

class UISearch() {

    constructor(search: ArrayList<UIRepo>) : this() {
        items = search
    }

    var items : ArrayList<UIRepo>? = null
}