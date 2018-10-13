package com.github.fcopardo.ui.views

import android.content.Context
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import com.github.fcopardo.model.Responses.GithubSearch
import com.github.fcopardo.model.ui.MainWindow
import com.github.fcopardo.ui.MainWindowAdapter
import com.github.fcopardo.ui.UI

class MainWindowUI : LinearLayout, UI<GithubSearch> {

    private lateinit var mainContent : RecyclerView
    private lateinit var adapter : MainWindowAdapter
    private var data : GithubSearch? = null

    constructor(context : Context) : super(context){
        init()
    }

    constructor(context : Context, attr : AttributeSet) : super(context, attr){
        init()
    }

    constructor(context : Context, attr : AttributeSet, defStyleAttr : Int) : super(context, attr, defStyleAttr){
        init()
    }

    private fun init(){
        orientation = LinearLayout.VERTICAL
        gravity = Gravity.CENTER
        mainContent = RecyclerView(context)
        mainContent.layoutParams = LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        addView(mainContent)

        var layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        mainContent.layoutManager = layoutManager
        adapter = MainWindowAdapter()
        mainContent.adapter = adapter
    }

    override fun setData(data: GithubSearch) {
        this.data = data
        adapter.setElements(this.data?.items!!, true)
    }

    override fun asView(): View {
        return this
    }

}