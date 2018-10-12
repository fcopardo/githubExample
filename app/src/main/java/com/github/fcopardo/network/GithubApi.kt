package com.github.fcopardo.network

import com.github.fcopardo.model.Responses.GithubSearch
import retrofit2.Call
import retrofit2.http.GET

interface GithubApi {

    @GET("search/repositories?q=android+in%3Aname+in%3Adescription+in%3Areadme&sort=stars&order=desc:topic=android")
    fun getAndroidProjects() : Call<GithubSearch>

}