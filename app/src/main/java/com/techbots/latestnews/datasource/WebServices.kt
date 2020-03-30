package com.techbots.latestnews.datasource

import android.util.Log
import com.techbots.latestnews.db.DataRepository
import com.techbots.latestnews.db.NewsInfo
import com.techbots.latestnews.network.APIInterface
import com.techbots.latestnews.utils.API_KEY
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WebServices(var apiInterface: APIInterface): DataSource {
    var pageCount:Int = 1
    override fun getNewsByCountry(uiCallBacks: DataRepository.UICallBacks, countryName: String) {
        Log.v(DataRepository::class.simpleName, "makeServerRequest")
        apiInterface.getTopHeadLines(countryName, API_KEY, pageCount)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { uiCallBacks.onRetrieveData()}
            .doOnTerminate { uiCallBacks.onRetrieveDataFinish() }
            .subscribe(object : Observer<NewsInfo> {
                override fun onComplete() {
                    Log.v(DataRepository::class.simpleName, "onComplete")
                }

                override fun onNext(newsInfo: NewsInfo) {
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

    override fun getNewsByEveryThing(uiCallBacks: DataRepository.UICallBacks, query: String) {
        Log.v(DataRepository::class.simpleName, "makeServerRequest")
        apiInterface.getEverything(query, API_KEY, pageCount)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { uiCallBacks.onRetrieveData()}
            .doOnTerminate { uiCallBacks.onRetrieveDataFinish() }
            .subscribe(object : Observer<NewsInfo> {
                override fun onComplete() {
                    Log.v(DataRepository::class.simpleName, "onComplete")
                }

                override fun onNext(newsInfo: NewsInfo) {
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

    override fun getNewsByCategory(uiCallBacks: DataRepository.UICallBacks, category: String) {
        Log.v(DataRepository::class.simpleName, "makeServerRequest")
        apiInterface.getTopHeadLinesByCatogery(category, API_KEY, pageCount)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { uiCallBacks.onRetrieveData()}
            .doOnTerminate { uiCallBacks.onRetrieveDataFinish() }
            .subscribe(object : Observer<NewsInfo> {
                override fun onComplete() {
                    Log.v(DataRepository::class.simpleName, "onComplete")
                }

                override fun onNext(newsInfo: NewsInfo) {
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