package com.techbots.latestnews.db

import android.util.Log
import com.techbots.latestnews.datasource.NewsArticle
import com.techbots.latestnews.network.APIInterface
import com.techbots.latestnews.utils.API_KEY
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class DataRepository(var apiInterface: APIInterface) {
    var pageCount:Int = 1

    interface UICallBacks {
        /**
         * Using this method we can start retriving stat from server
         */
        fun onRetrieveData()
        fun onRetrieveDataFinish()
        fun onRetrieveDateSuccess(newsArticle: List<NewsArticle>)
        fun onRetrieveDataError()
    }

    fun makeServerRequest(uiCallBacks: UICallBacks) {
        Log.v(DataRepository::class.simpleName, "makeServerRequest")
        apiInterface.getTopHeadLines("us","business", API_KEY, pageCount)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { uiCallBacks.onRetrieveData()}
            .doOnTerminate { uiCallBacks.onRetrieveDataFinish() }
            .subscribe(object : Observer<NewsInfo> {
                override fun onComplete() {
                    Log.v(DataRepository::class.simpleName, "onComplete")
                }

                override fun onNext(newsInfo: NewsInfo) {
                    pageCount += 1
                    Log.v(DataRepository::class.simpleName, "onNext " + newsInfo.articles.size)
                    uiCallBacks.onRetrieveDateSuccess(newsInfo.articles)
                }

                override fun onError(e: Throwable) {
                    Log.v(DataRepository::class.simpleName, "onError " + e.message)
                    uiCallBacks.onRetrieveDataError()
                }

                override fun onSubscribe(d: Disposable) {
                    Log.v(DataRepository::class.simpleName, "onSubscribe " + d.toString())
                }
            })
    }

}