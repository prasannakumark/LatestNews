package com.techbots.latestnews.datasource

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.techbots.latestnews.db.DataRepository
import com.techbots.latestnews.network.APIInterface
import com.techbots.latestnews.utils.API_KEY
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class NewsEverythingDataSource(
    var apiInterface: APIInterface,
    coroutineContext: CoroutineContext,
    var query:String) : PageKeyedDataSource<Int, NewsArticle>() {

    private val job = Job()
    private val scope = CoroutineScope(coroutineContext + job)

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, NewsArticle>
    ) {
        loadInitData(1, callback)
    }
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, NewsArticle>) {
        loadAfterData(params.key + 1, callback)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, NewsArticle>) {}

    fun loadInitData(pageCount:Int, callback: LoadInitialCallback<Int, NewsArticle>) {
        Log.v(DataRepository::class.simpleName, "makeServerRequest")
        scope.launch {
            try {
                val response = apiInterface.getEverything(query, API_KEY, pageCount)
                when{
                    response.isSuccessful -> {
                        val listing = response.body()?.articles
                        callback.onResult(listing ?: listOf(), 1, 2)
                    }
                }
            }catch (exception : Exception){
                Log.e("PostsDataSource", "Failed to fetch data!")
            }
        }
    }

    fun loadAfterData(pageCount:Int, callback: LoadCallback<Int, NewsArticle>) {
        Log.v(DataRepository::class.simpleName, "makeServerRequest")
        scope.launch {
            try {
                val response = apiInterface.getEverything(query, API_KEY, pageCount)
                when{
                    response.isSuccessful -> {
                        val listing = response.body()?.articles
                        callback.onResult(listing ?: listOf(), pageCount+1)
                    }
                }

            }catch (exception : Exception){
                Log.e("PostsDataSource", "Failed to fetch data!")
            }
        }
    }
}