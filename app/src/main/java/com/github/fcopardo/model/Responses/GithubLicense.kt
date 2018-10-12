package com.github.fcopardo.model.Responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GithubLicense {

    @SerializedName("key")
    @Expose
    val key: String? = null
    @SerializedName("name")
    @Expose
    val name: String? = null
    @SerializedName("spdx_id")
    @Expose
    val spdxId: String? = null
    @SerializedName("url")
    @Expose
    val url: String? = null
    @SerializedName("node_id")
    @Expose
    val nodeId: String? = null

}