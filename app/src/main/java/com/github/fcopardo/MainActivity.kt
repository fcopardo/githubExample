package com.github.fcopardo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.github.fcopardo.data.DataManager
import com.github.fcopardo.model.ui.UISearch
import com.github.fcopardo.ui.MainUI
import com.github.fcopardo.ui.views.MainWindowUI


/**
 * The main activity ignores both how the UI and data sources work. MainUI is a plain interface
 * representing the UI's possible actions, and how to deliver the necessary data for the UI.
 * DataManager acts as the gateway towards the data. The activity only acts as a link between
 * DataManager and MainUI
 */
class MainActivity : AppCompatActivity() {


    lateinit var myUI : MainUI<UISearch>
    /**
     * The listener acts as the notification mechanism for the data retrieval operations.
     * A reference in kept in order to void it when the app leaves the foreground.
     */
    private var searchListener : DataManager.UISearchListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myUI = MainWindowUI(this)
        setContentView(myUI as View)
    }

    override fun onResume() {
        super.onResume()
        setListener(true)
        DataManager.getUISearch()
    }

    override fun onBackPressed() {
        if(myUI.shouldLeave()){
            super.onBackPressed()
        }else{
            myUI.switchContents()
        }
    }

    override fun onStop() {
        super.onStop()
        setListener(false)
    }

    fun setListener(isOn : Boolean){
        searchListener = if(isOn) {
            object : DataManager.UISearchListener {
                override fun onResults(results: UISearch) {
                    myUI.setData(results)
                }
            }
        }else{
            null
        }
        DataManager.listener = searchListener
    }

}