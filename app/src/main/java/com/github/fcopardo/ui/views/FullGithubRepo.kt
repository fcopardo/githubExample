package com.github.fcopardo.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import com.github.fcopardo.R

/**
 * Specific cell representing a repository
 */
class FullGithubRepo : AbstractGithubRepo {

    private lateinit var projectName : TextView
    private lateinit var stars : TextView
    private lateinit var description : TextView
    private lateinit var language : TextView
    private lateinit var license : TextView

    constructor(context : Context) : super(context)
    constructor(context : Context, attr : AttributeSet) : super(context, attr)
    constructor(context : Context, attr : AttributeSet, defStyleAttr : Int) : super(context, attr, defStyleAttr)

    override fun setContent() {

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.full_github_repo, this, true)

        projectName = findViewById(R.id.txt_project_name)
        stars = findViewById(R.id.txt_stars)
        description = findViewById(R.id.txt_description)
        language = findViewById(R.id.txt_language)
        license = findViewById(R.id.txt_license)
    }

    override fun resetViews() {

        projectName.text = getData().projectName
        stars.text = getData().stars
        description.text = getData().description
        language.text = getData().language!!
        license.text = getData().license
    }

    override fun setActions(repositoryCellActions: AbstractGithubRepo.RepositoryCellActions){
        super.setActions(repositoryCellActions)
        setOnClickListener {
            repositoryCellActions.onClickElement(getData())
        }
    }
}