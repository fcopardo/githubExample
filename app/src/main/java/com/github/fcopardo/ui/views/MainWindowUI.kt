package com.github.fcopardo.ui.views

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.constraint.ConstraintLayout
import android.support.v4.view.AsyncLayoutInflater
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.github.fcopardo.R
import com.github.fcopardo.model.ui.UIRepo
import com.github.fcopardo.model.ui.UISearch
import com.github.fcopardo.ui.MainUI
import com.github.fcopardo.ui.recycler.MainWindowAdapter


/**
 * Main window of the app.
 */
class MainWindowUI : FrameLayout, MainUI<UISearch> {

    private lateinit var mainContent : RecyclerView
    private lateinit var adapter : MainWindowAdapter
    private var data : UISearch? = null

    private lateinit var detail : ConstraintLayout
    private lateinit var name : TextView
    private lateinit var description : TextView
    private lateinit var readme : TextView
    private lateinit var toRepository : Button
    private lateinit var progressBar : ProgressBar

    private var animationLength = 0L

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

        animationLength = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()

        val inflater = AsyncLayoutInflater(context)
        inflater.inflate(R.layout.repo_detail, this) { view, resid, parent ->
            detail = view.findViewById(R.id.root_detail)
            name = view.findViewById(R.id.txt_project_name)
            description = view.findViewById(R.id.txt_description)
            readme = view.findViewById(R.id.txt_readme)
            toRepository = view.findViewById(R.id.btb_go)

            detail.visibility = View.GONE
            addView(detail)
        }

        mainContent = RecyclerView(context)
        mainContent.layoutParams = FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        addView(mainContent)

        var layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        mainContent.layoutManager = layoutManager
        adapter = MainWindowAdapter()
        mainContent.adapter = adapter

        setCellListener(object : AbstractGithubRepo.RepositoryCellActions{
            override fun onClickElement(payload: UIRepo) {
                switchContents()
                setDetail(payload)
            }
        })
    }

    fun setCellListener(listener : AbstractGithubRepo.RepositoryCellActions){
        adapter.setListener(listener)
    }

    fun setDetail(data: UIRepo) {
        name.text = data.projectName
        description.text = data.description
        //readme.text = data
        toRepository.setOnClickListener {
            var i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(data.url)
            context.startActivity(i)
        }
    }

    override fun setData(data: UISearch) {
        this.data = data
        adapter.setElements(this.data?.items!!, true)
    }

    override fun asView(): View {
        return this
    }

    override fun switchContents(){
        if(mainContent.visibility!=GONE){
            animate(detail, mainContent)
        }else{
            animate(mainContent, detail)
        }
    }

    override fun shouldLeave() : Boolean {
        return mainContent.visibility== View.VISIBLE
    }

    private fun animate(toShow : View, toHide : View) {

        toShow.alpha = 0f
        toShow.visibility = View.VISIBLE
        toShow.animate()
                .alpha(1f)
                .setDuration(animationLength)
                .setListener(null)
        toHide.animate()
                .alpha(0f)
                .setDuration(animationLength)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        toHide.visibility = View.GONE
                    }
                })
    }

}