package com.github.fcopardo.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BaseRetrofitBuilder {

    var retrofit : Retrofit

    constructor(baseUrl: String){
        retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create()).build()
    }

}