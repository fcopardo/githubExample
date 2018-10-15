package com.github.fcopardo.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.github.fcopardo.model.Responses.GithubSearch
import com.github.fcopardo.network.call.GithubCall
import com.github.fcopardo.ui.views.MainWindowUI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var myUI : MainUI<GithubSearch>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myUI = MainWindowUI(this)
        setContentView(myUI as View)
        GithubCall.search.getAndroidProjects().enqueue(object : Callback<GithubSearch>{
            override fun onFailure(call: Call<GithubSearch>?, t: Throwable?) {
                Log.e("xapo test", "failed by "+t?.message)
            }

            override fun onResponse(call: Call<GithubSearch>?, response: Response<GithubSearch>?) {
                Log.e("xapo test", "Call successfull "+call?.request()?.url())
                if(response?.code() in 200..299){
                    myUI.setData(response?.body()!!)
                }
            }

        })
    }

    override fun onBackPressed() {
        if(myUI.shouldLeave()){
            super.onBackPressed()
        }else{
            myUI.switchContents()
        }
    }



}