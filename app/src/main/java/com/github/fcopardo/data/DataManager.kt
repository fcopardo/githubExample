package com.github.fcopardo.data

import android.os.AsyncTask
import android.util.Log
import com.github.fcopardo.data.network.call.GithubCall
import com.github.fcopardo.model.responses.GithubRepository
import com.github.fcopardo.model.responses.GithubSearch
import com.github.fcopardo.model.ui.UIRepo
import com.github.fcopardo.model.ui.UISearch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

/**
 * Intended to be the single source of truth. If database operations were added,
 * they should appear here.
 */
class DataManager {

    interface UISearchListener{
        fun onResults(results : UISearch)
    }

    /**
     * Converts the response into models for the views.
     */
    class SearchPostProcessor : AsyncTask<Void, Void, ArrayList<UIRepo>> {

        private var dataToProcess : GithubSearch

        constructor(data : GithubSearch){
            dataToProcess = data
        }

        override fun doInBackground(vararg params: Void?): ArrayList<UIRepo> {
            return transformData()
        }

        /**
         * Method added for testability.
         */
        fun transformData() : ArrayList<UIRepo> {
            var results : ArrayList<UIRepo> = ArrayList()
            for(node : GithubRepository in dataToProcess.items!!){
                var license = when{
                    node.license == null || node.license?.name == null -> "Other"
                    else -> node.license?.name
                }
                var language = when{
                    node.language?.trim() == "" -> "Misc"
                    node.language == null -> "Misc"
                    else -> {
                        node.language!!
                    }
                }
                results.add(UIRepo(node.name, node.stargazersCount.toString(), node.description, language, license, node.htmlUrl))
            }
            return results
        }

        override fun onPostExecute(result: ArrayList<UIRepo>?) {
            listener?.onResults(UISearch(result!!))
        }
    }

    companion object {
        var listener : UISearchListener? = null

        fun getUISearch(){
            GithubCall.search.getAndroidProjects().enqueue(object : Callback<GithubSearch> {
                override fun onFailure(call: Call<GithubSearch>?, t: Throwable?) {
                    Log.e("xapo test", "failed by "+t?.message)
                }

                override fun onResponse(call: Call<GithubSearch>?, response: Response<GithubSearch>?) {
                    Log.e("xapo test", "Call in executor successfull "+call?.request()?.url())
                    if(response?.code() in 200..299){
                        var processor = SearchPostProcessor(response!!.body()!!)
                        processor.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
                    }
                }

            })
        }
    }

}