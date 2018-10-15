package com.github.fcopardo.model.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GithubSearch {

    @SerializedName("total_count")
    @Expose
    var totalCount: Int? = null
    @SerializedName("incomplete_results")
    @Expose
    var incompleteResults: Boolean? = null
    @SerializedName("items")
    @Expose
    var items: ArrayList<GithubRepository>? = null
}