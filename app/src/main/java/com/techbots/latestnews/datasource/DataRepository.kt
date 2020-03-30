package com.techbots.latestnews.db

import com.techbots.latestnews.datasource.DataSource
import com.techbots.latestnews.datasource.NewsArticle
import com.techbots.latestnews.datasource.WebServices
import javax.inject.Inject

/**
 * This is class will all DataSource related work like communicating with
 * server for fetch data and it will notify to the listeners
 */
class DataRepository(var webServices: WebServices): DataSource {
    interface UICallBacks {
        /**
         * Using this method we can notify to user the server repsonses
         */
        fun onRetrieveData()
        fun onRetrieveDataFinish()
        fun onRetrieveDateSuccess(newsArticle: List<NewsArticle>)
        fun onRetrieveDataError()
    }

    override fun getNewsByCategory(uiCallBacks: UICallBacks, category: String) {
        webServices.getNewsByCategory(uiCallBacks,category)
    }

    override fun getNewsByCountry(uiCallBacks: UICallBacks, countryName: String) {
        webServices.getNewsByCountry(uiCallBacks,countryName)
    }

    override fun getNewsByEveryThing(uiCallBacks: UICallBacks, query: String) {
        webServices.getNewsByEveryThing(uiCallBacks, query)
    }
}