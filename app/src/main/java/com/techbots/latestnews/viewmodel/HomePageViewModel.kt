package com.techbots.latestnews.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.nfc.tech.MifareUltralight
import android.nfc.tech.MifareUltralight.PAGE_SIZE
import android.os.AsyncTask
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.techbots.latestnews.R
import com.techbots.latestnews.datasource.NewsArticle
import com.techbots.latestnews.datasource.NewsDAO
import com.techbots.latestnews.db.DataRepository
import com.techbots.latestnews.utils.isOnline
import com.techbots.latestnews.view.NewArticleAdapter
import org.jetbrains.annotations.NotNull
import javax.inject.Inject

/**
 * This is class mediator between view and data source.
 */
class HomePageViewModel(private val context: Context, private val newsDAO: NewsDAO) : BaseViewModel(),
    DataRepository.UICallBacks {
    val loadingVisibility:MutableLiveData<Int> = MutableLiveData()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val recyclerListener = object: RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val visibleItemCount: Int = recyclerView.layoutManager!!.childCount
            val totalItemCount: Int = recyclerView.layoutManager!!.itemCount
            val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition: Int = linearLayoutManager.findFirstVisibleItemPosition()
            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                && firstVisibleItemPosition >= 0 && totalItemCount >= MifareUltralight.PAGE_SIZE) {
                loadMoreData()
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
        }
    }
    @NotNull
    private lateinit var callBacks: CallBacks
    @Inject
    lateinit var dataRepository: DataRepository
    val newArticleListAdapter: NewArticleAdapter = NewArticleAdapter()

    interface CallBacks {
        fun networkError()
    }

    /**
     * Load more data when do scroll up(it like pagination)
     */
    fun loadMoreData() {
        if(isOnline(context)) {
            dataRepository.makeServerRequest(this)
        }
    }

    /**
     * This is method will be called first time of application lunch.
     * Based on the isOnline method it will perform required action.
     */
    fun makeServerRequest() {
        if(isOnline(context)) {
            dataRepository.makeServerRequest(this)
        } else {
            callBacks.networkError()
        }
    }

    /**
     * When user is in offline, we will latest offline data
     */
    fun loadOfflineArticles() {
        LoadAsyncTask(ArrayList<NewsArticle>(),false).execute()
    }

    override fun onRetrieveData() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    override fun onRetrieveDataFinish() {
        loadingVisibility.value = View.GONE
    }

    override fun onRetrieveDateSuccess(newsArticle: List<NewsArticle>) {
        LoadAsyncTask(newsArticle,true).execute()
        newArticleListAdapter.updateArticleList(newsArticle)
    }

    override fun onRetrieveDataError() {
        errorMessage.value = R.string.post_error
    }

    companion object {
        fun setCallBacks(homePageViewModel: HomePageViewModel, callBacks: CallBacks) {
            homePageViewModel.callBacks = callBacks
        }
    }

    //TODO: we have to implement fetch local data using RXJava in background thread
    @SuppressLint("StaticFieldLeak")
    inner class LoadAsyncTask(var newsArticle: List<NewsArticle>, val isInsert:Boolean): AsyncTask<String, String, String>() {
        override fun doInBackground(vararg p0: String?): String {
            if(isInsert) {
                newsDAO.deleteAll()
                newsDAO.insertAll(newsArticle)
            } else {
                newsArticle = newsDAO.all
            }
            return ""
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            if(!isInsert) {
                onRetrieveDataFinish()
                newArticleListAdapter.updateArticleList(newsArticle)
            }
        }

        override fun onPreExecute() {
            super.onPreExecute()
        }
    }
}