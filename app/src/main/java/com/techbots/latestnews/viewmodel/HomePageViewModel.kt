package com.techbots.latestnews.viewmodel

import android.content.Context
import android.nfc.tech.MifareUltralight
import android.util.Log
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
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.schedulers.Schedulers
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
    fun makeServerRequest(category: String) {
        if(isOnline(context)) {
            if(category == "For You") {
                dataRepository.makeServerRequest(this)
            } else {
                dataRepository.makeServerRequestByCatagory(this,category)
            }
        } else {
            callBacks.networkError()
        }
    }

    /**
     * When user is in offline, we will latest offline data
     */
    fun loadOfflineArticles() {
        onRetrieveDataFinish()

        Observable.create<List<NewsArticle>>{ subscribe->
                try {
                    subscribe.onNext(newsDAO.all)
                    subscribe.onComplete()
                }catch (e:Exception) {
                    subscribe.onError(e)
                }

        }.subscribeOn(Schedulers.newThread()).observeOn(mainThread()).subscribe {
            onNext -> newArticleListAdapter.updateArticleList(onNext)
        }
    }

    override fun onRetrieveData() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    override fun onRetrieveDataFinish() {
        loadingVisibility.value = View.GONE
    }

    override fun onRetrieveDateSuccess(newsArticle: List<NewsArticle>) {

        Observable.create<Unit>{ subscribe->
            try {
                subscribe.onNext(newsDAO.deleteAll())
                subscribe.onComplete()
            }catch (e:Exception) {
                subscribe.onError(e)
            }

        }.subscribeOn(Schedulers.newThread()).observeOn(mainThread()).subscribe {
                onNext -> Log.v(HomePageViewModel::class.simpleName, "Delete Items " + onNext.toString())
            Observable.create<Unit>{ subscribe->
                try {
                    subscribe.onNext(newsDAO.insertAll(newsArticle))
                    subscribe.onComplete()
                }catch (e:Exception) {
                    subscribe.onError(e)
                }

            }.subscribeOn(Schedulers.newThread()).observeOn(mainThread()).subscribe {
                    onNext -> Log.v(HomePageViewModel::class.simpleName, "Inseting an items " + onNext.toString())
                    newArticleListAdapter.updateArticleList(newsArticle)
            }
        }




    }

    override fun onRetrieveDataError() {
        errorMessage.value = R.string.post_error
    }

    companion object {
        fun setCallBacks(homePageViewModel: HomePageViewModel, callBacks: CallBacks) {
            homePageViewModel.callBacks = callBacks
        }
    }
}