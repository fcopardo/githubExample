package com.github.fcopardo.ui.recycler

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.github.fcopardo.model.ui.UIRepo
import com.github.fcopardo.ui.views.AbstractGithubRepo
import com.github.fcopardo.ui.views.FullGithubRepo
import com.github.fcopardo.ui.views.GenericViewHolder

/**
 * Adapter for the main window.
 */
class MainWindowAdapter : RecyclerView.Adapter<GenericViewHolder<AbstractGithubRepo, UIRepo>>() {

    private var elements : ArrayList<UIRepo> = ArrayList()
    private var cellActions : AbstractGithubRepo.RepositoryCellActions? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): GenericViewHolder<AbstractGithubRepo, UIRepo> {
        var view : AbstractGithubRepo = createView(p0.context)
        val lp = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        view.layoutParams = lp
        view.setData(elements[p1])
        return GenericViewHolder(view)
    }

    override fun getItemCount(): Int {
        return elements.size
    }

    fun setElements(someElements : ArrayList<UIRepo>, replace : Boolean) : MainWindowAdapter {
        if(replace) elements = someElements
        else elements.addAll(someElements)
        notifyDataSetChanged()
        return this
    }

    fun setListener(listener : AbstractGithubRepo.RepositoryCellActions){
        cellActions = listener
    }

    override fun onBindViewHolder(p0: GenericViewHolder<AbstractGithubRepo, UIRepo>, p1: Int) {
        p0.resetData(elements[p1])
        p0.getView().setActions(cellActions!!)
    }

    private fun createView(context : Context) : AbstractGithubRepo{
        return FullGithubRepo(context)
    }

    fun clear(){
        var size : Int = elements.size
        elements.clear()
        notifyItemRangeRemoved(0, size)
    }

}