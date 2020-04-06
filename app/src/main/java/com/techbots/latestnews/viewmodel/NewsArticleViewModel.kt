package com.techbots.latestnews.viewmodel

import android.nfc.tech.MifareUltralight
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.techbots.latestnews.R
import com.techbots.latestnews.datasource.NewsArticle
import com.techbots.latestnews.db.DataRepository
import com.techbots.latestnews.view.NewArticleAdapter
import org.jetbrains.annotations.NotNull
import javax.inject.Inject

/**
 * This class is responsible for preparing and managing the data for view(ie: activity or fragment)
 */
class NewsArticleViewModel : BaseViewModel() {
    val loadingVisibility:MutableLiveData<Int> = MutableLiveData()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    @Inject
    lateinit var dataRepository: DataRepository

    @NotNull
    private lateinit var callBacks: CallBacks
    val newArticleListAdapter: NewArticleAdapter = NewArticleAdapter()

    interface CallBacks {
        fun networkError()
    }

    companion object {
        fun setCallBacks(homePageViewModel: NewsArticleViewModel, callBacks: CallBacks) {
            homePageViewModel.callBacks = callBacks
        }
    }

    fun getNewsByCategory(category: String) {
        dataRepository.getNewsByCategory(category)
    }

    fun getNewsByEveryThing(query: String) {
        dataRepository.getNewsByEveryThing(query)
    }

    fun getNewsByCountry(countryName: String) {
        dataRepository.getNewsByCountry(countryName)
    }

    fun getPosts() : LiveData<PagedList<NewsArticle>> {
        return dataRepository.getPosts()
    }
}