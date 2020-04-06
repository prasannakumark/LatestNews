package com.techbots.latestnews.db

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.techbots.latestnews.datasource.NewsArticle
import com.techbots.latestnews.datasource.WebServices

/**
 * This is class will all DataSource related work like communicating with
 * server for fetch data and it will notify to the listeners
 */
class DataRepository(var webServices: WebServices) {

    fun getNewsByCategory(category: String) {
        webServices.getNewsByCategory(category)
    }

    fun getNewsByCountry(countryName: String) {
        webServices.getNewsByCountry(countryName)
    }

    fun getNewsByEveryThing(query: String) {
        webServices.getNewsByEveryThing(query)
    }

    fun getPosts() : LiveData<PagedList<NewsArticle>> {
       return webServices.getNewsArticles()
    }
}