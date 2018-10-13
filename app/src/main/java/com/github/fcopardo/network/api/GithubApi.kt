package com.github.fcopardo.network.api

import com.github.fcopardo.model.Responses.GithubSearch
import retrofit2.Call
import retrofit2.http.GET

interface GithubApi {

    @GET("search/repositories?q=android&sort=stars&order=desc:topic=android")
    fun getAndroidProjects() : Call<GithubSearch>

}