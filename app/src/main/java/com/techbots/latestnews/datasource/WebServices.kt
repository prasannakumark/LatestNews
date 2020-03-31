package com.techbots.latestnews.datasource

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.techbots.latestnews.db.DataRepository
import com.techbots.latestnews.network.APIInterface

class WebServices(var apiInterface: APIInterface): DataSource {
    lateinit var newsArticlesLiveData  :LiveData<PagedList<NewsArticle>>
    fun getNewsArticles():LiveData<PagedList<NewsArticle>> = newsArticlesLiveData

    override fun getNewsByCountry(uiCallBacks: DataRepository.UICallBacks, countryName: String) {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(10)
            .setPageSize(100).build();
        val dataSourceFactory = object : androidx.paging.DataSource.Factory<Int, NewsArticle>() {
            override fun create(): androidx.paging.DataSource<Int, NewsArticle> {
                return NewsCountryDataSource(apiInterface,uiCallBacks, countryName)
            }
        }

        newsArticlesLiveData = LivePagedListBuilder<Int, NewsArticle>(dataSourceFactory, config).build()
    }

    override fun getNewsByEveryThing(uiCallBacks: DataRepository.UICallBacks, query: String) {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(10)
            .setPageSize(100).build();
        val dataSourceFactory = object : androidx.paging.DataSource.Factory<Int, NewsArticle>() {
            override fun create(): androidx.paging.DataSource<Int, NewsArticle> {
                return NewsEverythingDataSource(apiInterface,uiCallBacks,query)
            }
        }

        newsArticlesLiveData = LivePagedListBuilder<Int, NewsArticle>(dataSourceFactory, config).build()
    }

    override fun getNewsByCategory(uiCallBacks: DataRepository.UICallBacks, category: String) {
        Log.v(DataRepository::class.simpleName, "makeServerRequest")
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(10)
            .setPageSize(100).build();
        val dataSourceFactory = object : androidx.paging.DataSource.Factory<Int, NewsArticle>() {
            override fun create(): androidx.paging.DataSource<Int, NewsArticle> {
                return NewsCategoryDataSource(apiInterface,uiCallBacks,category)
            }
        }

        newsArticlesLiveData = LivePagedListBuilder<Int, NewsArticle>(dataSourceFactory, config).build()
    }
}