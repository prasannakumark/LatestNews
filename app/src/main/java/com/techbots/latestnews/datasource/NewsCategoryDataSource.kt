package com.techbots.latestnews.datasource

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.techbots.latestnews.db.DataRepository
import com.techbots.latestnews.db.NewsInfo
import com.techbots.latestnews.network.APIInterface
import com.techbots.latestnews.utils.API_KEY
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class NewsCategoryDataSource(var apiInterface: APIInterface,
                             var uiCallBacks: DataRepository.UICallBacks,
                             var category:String) : PageKeyedDataSource<Int, NewsArticle>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, NewsArticle>
    ) {
        loadInitData(1,callback)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, NewsArticle>) {
        loadAfterData(params.key+1,callback)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, NewsArticle>) {}

    fun loadInitData(pageCount:Int, callback: LoadInitialCallback<Int, NewsArticle>) {
        Log.v(DataRepository::class.simpleName, "makeServerRequest")
        apiInterface.getTopHeadLinesByCatogery(category, API_KEY, pageCount)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<NewsInfo> {
                override fun onComplete() {
                    Log.v(DataRepository::class.simpleName, "onComplete")
                }

                override fun onNext(newsInfo: NewsInfo) {
                    Log.v(DataRepository::class.simpleName, "onNext " + newsInfo.articles.size)
                    callback.onResult(newsInfo.articles ?: listOf(), 1, 2)
                    uiCallBacks.onRetrieveDateSuccess(newsInfo.articles)
                }

                override fun onError(e: Throwable) {
                    Log.v(DataRepository::class.simpleName, "onError " + e.message)
                }

                override fun onSubscribe(d: Disposable) {
                    Log.v(DataRepository::class.simpleName, "onSubscribe " + d.toString())
                }
            })
    }

    fun loadAfterData(pageCount:Int, callback: LoadCallback<Int, NewsArticle>) {
        Log.v(DataRepository::class.simpleName, "makeServerRequest")
        apiInterface.getTopHeadLinesByCatogery(category, API_KEY, pageCount)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<NewsInfo> {
                override fun onComplete() {}

                override fun onNext(newsInfo: NewsInfo) {
                    Log.v(DataRepository::class.simpleName, "onNext " + newsInfo.articles.size)
                    uiCallBacks.onRetrieveDateSuccess(newsInfo.articles)
                    callback.onResult(newsInfo.articles,pageCount+1)
                }

                override fun onError(e: Throwable) {}

                override fun onSubscribe(d: Disposable) {}
            })
    }
}