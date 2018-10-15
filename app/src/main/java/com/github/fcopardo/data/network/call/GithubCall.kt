package com.github.fcopardo.data.network.call

import com.github.fcopardo.data.network.BaseRetrofitBuilder
import com.github.fcopardo.data.network.api.GithubApi

class GithubCall {

    companion object {
        var search : GithubApi = BaseRetrofitBuilder("https://api.github.com/", GithubApi::class.java).api
    }

}