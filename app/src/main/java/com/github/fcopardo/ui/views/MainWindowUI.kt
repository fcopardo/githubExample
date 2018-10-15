package com.github.fcopardo.ui.views

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import com.github.fcopardo.model.Responses.GithubRepository
import com.github.fcopardo.model.Responses.GithubSearch
import com.github.fcopardo.ui.MainUI
import com.github.fcopardo.ui.recycler.MainWindowAdapter

class MainWindowUI : LinearLayout, MainUI<GithubSearch> {

    private lateinit var mainContent : RecyclerView
    private lateinit var adapter : MainWindowAdapter
    private lateinit var detailWindow : DetailWindowUI
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
        detailWindow = DetailWindowUI(context)
        detailWindow.layoutParams = LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        addView(detailWindow)
        detailWindow.visibility = View.GONE
        detailWindow.setBehavior(object : DetailWindowUI.Behavior{
            override fun openRepository(url: String) {
                var i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                context.startActivity(i)
            }

        })

        setCellListener(object : AbstractGithubRepo.RepositoryCellActions{
            override fun onClickElement(payload: GithubRepository) {
                switchContents()
                detailWindow.setData(payload)
            }

        })
    }

    fun setCellListener(listener : AbstractGithubRepo.RepositoryCellActions){
        adapter.setListener(listener)
    }

    override fun switchContents(){
        if(mainContent.visibility!=GONE){
            detailWindow.visibility = View.VISIBLE
            mainContent.visibility = GONE
        }else{
            detailWindow.visibility = View.GONE
            mainContent.visibility = View.VISIBLE
        }
    }

    override fun shouldLeave() : Boolean {
        return mainContent.visibility== View.VISIBLE
    }

    override fun setData(data: GithubSearch) {
        this.data = data
        adapter.setElements(this.data?.items!!, true)
    }

    override fun asView(): View {
        return this
    }

}