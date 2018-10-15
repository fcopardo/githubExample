package com.github.fcopardo.ui.views

import android.content.Context
import android.support.v7.widget.CardView
import android.util.AttributeSet
import android.view.View
import com.github.fcopardo.model.ui.UIRepo
import com.github.fcopardo.ui.UI

/**
 * Superclass of the recyclerview's cells. Allows creating several variations of cells.
 * The cells are supposed to be self contained view classes and the adapter only needs
 * to pass the model to them in order to avoid errors due to recycling.
 */
abstract class AbstractGithubRepo : CardView, UI<UIRepo> {

    interface RepositoryCellActions {
        fun onClickElement(payload : UIRepo)
    }

    private lateinit var data : UIRepo
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

    override fun setData(data : UIRepo){
        this.data = data
        resetViews()
    }

    protected fun getData(): UIRepo {
        return data
    }

    override fun asView() : View {
        return this
    }

    abstract fun setContent()
    abstract fun resetViews()

}