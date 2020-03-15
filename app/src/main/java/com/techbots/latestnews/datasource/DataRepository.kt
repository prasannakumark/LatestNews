package com.techbots.latestnews.db

import android.util.Log
import com.techbots.latestnews.datasource.ImageInfo
import com.techbots.latestnews.network.APIInterface
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * This is class will all DataSource related work like communicating with
 * server for fetch data and it will notify to the listeners
 */
class DataRepository(var apiInterface: APIInterface) {

    interface UICallBacks {
        /**
         * Using this method we can notify to user the server repsonses
         */
        fun onRetrieveData()
        fun onRetrieveDataFinish()
        fun onRetrieveDateSuccess(messageInfo: ImageInfo)
        fun onRetrieveDataError()
    }

    /**
     * Using this method we can fetch data from server.
     * Here using rxJava it will do appropriate work base on its requires thread.
     * Then it will be notify to listeners
     */
    fun makeServerRequest(uiCallBacks: UICallBacks) {
        Log.v(DataRepository::class.simpleName, "makeServerRequest")
        apiInterface.getDogImage()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { uiCallBacks.onRetrieveData()}
            .doOnTerminate { uiCallBacks.onRetrieveDataFinish() }
            .subscribe(object : Observer<ImageInfo> {
                override fun onComplete() {
                    Log.v(DataRepository::class.simpleName, "onComplete")
                }

                override fun onNext(imageInfo: ImageInfo) {
                    uiCallBacks.onRetrieveDateSuccess(imageInfo)
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