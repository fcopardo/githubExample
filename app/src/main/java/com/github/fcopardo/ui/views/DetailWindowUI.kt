package com.github.fcopardo.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.github.fcopardo.R
import com.github.fcopardo.model.Responses.GithubRepository
import com.github.fcopardo.ui.UI

class DetailWindowUI : LinearLayout, UI<GithubRepository> {

    interface Behavior {
        fun openRepository(url : String)
    }

    private lateinit var data : GithubRepository
    private lateinit var name : TextView
    private lateinit var description : TextView
    private lateinit var readme : TextView
    private lateinit var toRepository : Button
    private var behavior : DetailWindowUI.Behavior? = null

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
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.repo_detail, this, true)

        name = findViewById(R.id.txt_project_name)
        description = findViewById(R.id.txt_description)
        readme = findViewById(R.id.txt_readme)
        toRepository = findViewById(R.id.btb_go)
    }

    fun setBehavior(behavior: DetailWindowUI.Behavior){
        this.behavior = behavior
        toRepository!!.setOnClickListener {
            behavior?.openRepository(data.url!!)
        }
    }

    override fun setData(data: GithubRepository) {
        this.data = data

        name.text = data.name
        description.text = data.description
        //readme.text = data
        toRepository.setOnClickListener {
            behavior?.openRepository(data.htmlUrl!!)
        }
    }

    override fun asView(): View {
        return this
    }
}