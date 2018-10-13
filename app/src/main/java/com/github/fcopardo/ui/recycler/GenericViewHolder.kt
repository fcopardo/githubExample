package com.github.fcopardo.ui.views

import android.support.v7.widget.RecyclerView
import android.view.View
import com.github.fcopardo.ui.UI


class GenericViewHolder<T : UI<X>, X> : RecyclerView.ViewHolder {

    constructor(view : T) : super(view as View)

    fun getView() : T {
        return this.itemView as T
    }

    fun resetData(data : X){
        getView().setData(data)
    }
}