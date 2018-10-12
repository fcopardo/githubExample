package com.github.fcopardo.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.fcopardo.model.ui.MainWindow

class MainActivity : AppCompatActivity() {

    lateinit var myUI : UI<MainWindow>
    lateinit var myMainWindow: MainWindow

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }



}