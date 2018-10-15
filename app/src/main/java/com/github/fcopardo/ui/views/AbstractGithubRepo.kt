package com.github.fcopardo.ui.views

import android.content.Context
import android.support.v7.widget.CardView
import android.util.AttributeSet
import android.view.View
import com.github.fcopardo.model.Responses.GithubRepository
import com.github.fcopardo.ui.UI

abstract class AbstractGithubRepo : CardView, UI<GithubRepository> {

    interface RepositoryCellActions {
        fun onClickElement(payload : GithubRepository)
    }

    private lateinit var data : GithubRepository
    private var repositoryCellActions : AbstractGithubRepo.RepositoryCellActions? = null

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
        cardElevation = 16F
        useCompatPadding = true
        radius = 8F
        setContent()
    }

    open fun setActions(repositoryCellActions: AbstractGithubRepo.RepositoryCellActions){
        this.repositoryCellActions = repositoryCellActions
    }

    override fun setData(data : GithubRepository){
        this.data = data
        resetViews()
    }

    protected fun getData(): GithubRepository {
        return data
    }

    override fun asView() : View {
        return this
    }

    abstract fun setContent()
    abstract fun resetViews()

}