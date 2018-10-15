package com.github.fcopardo.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BaseRetrofitBuilder<T> {

    var retrofit : Retrofit
    var api : T

    constructor(baseUrl: String, apiClass : Class<T>){
        retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create()).build()
        api = retrofit.create(apiClass)
    }

}