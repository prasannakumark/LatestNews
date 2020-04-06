package com.techbots.latestnews.datasource

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.techbots.latestnews.db.DataRepository
import com.techbots.latestnews.network.APIInterface
import kotlinx.coroutines.Dispatchers

class WebServices(var apiInterface: APIInterface) {
    lateinit var newsArticlesLiveData  :LiveData<PagedList<NewsArticle>>
    fun getNewsArticles():LiveData<PagedList<NewsArticle>> = newsArticlesLiveData

    fun getNewsByCountry(countryName: String) {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(10)
            .setPageSize(100).build();
        val dataSourceFactory = object : androidx.paging.DataSource.Factory<Int, NewsArticle>() {
            override fun create(): androidx.paging.DataSource<Int, NewsArticle> {
                return NewsCountryDataSource(apiInterface, Dispatchers.IO, countryName)
            }
        }

        newsArticlesLiveData = LivePagedListBuilder<Int, NewsArticle>(dataSourceFactory, config).build()
    }

    fun getNewsByEveryThing(query: String) {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(10)
            .setPageSize(100).build();
        val dataSourceFactory = object : androidx.paging.DataSource.Factory<Int, NewsArticle>() {
            override fun create(): androidx.paging.DataSource<Int, NewsArticle> {
                return NewsEverythingDataSource(apiInterface, Dispatchers.IO, query)
            }
        }

        newsArticlesLiveData = LivePagedListBuilder<Int, NewsArticle>(dataSourceFactory, config).build()
    }

    fun getNewsByCategory(category: String) {
        Log.v(DataRepository::class.simpleName, "makeServerRequest")
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(10)
            .setPageSize(100).build();
        val dataSourceFactory = object : androidx.paging.DataSource.Factory<Int, NewsArticle>() {
            override fun create(): androidx.paging.DataSource<Int, NewsArticle> {
                return NewsCategoryDataSource(apiInterface, Dispatchers.IO, category)
            }
        }

        newsArticlesLiveData = LivePagedListBuilder<Int, NewsArticle>(dataSourceFactory, config).build()
    }
}