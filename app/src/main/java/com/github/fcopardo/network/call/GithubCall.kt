package com.github.fcopardo.network.call

import com.github.fcopardo.network.BaseRetrofitBuilder
import com.github.fcopardo.network.api.GithubApi

class GithubCall {

    companion object {
        var search : GithubApi = BaseRetrofitBuilder("https://api.github.com/", GithubApi::class.java).api
    }

}