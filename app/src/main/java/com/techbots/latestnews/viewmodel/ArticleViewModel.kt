package com.techbots.latestnews.viewmodel

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.MutableLiveData
import com.techbots.latestnews.datasource.NewsArticle
import com.techbots.latestnews.utils.EXTRA_OBJECT
import com.techbots.latestnews.view.NewsDetailsActivity
import java.io.Serializable

class ArticleViewModel(var context: Context):BaseViewModel() {
    private lateinit var newsArticle: NewsArticle
    private val title = MutableLiveData<String>()
    private val description = MutableLiveData<String>()
    private val imageUrl = MutableLiveData<String>()
    private val dateView = MutableLiveData<String>()
    private val source = MutableLiveData<String>()
    private val author = MutableLiveData<String>()
    private val url = MutableLiveData<String>()
    private val content = MutableLiveData<String>()

    var onClickListener = View.OnClickListener {
        startDetailsActivity()
    }

    private fun startDetailsActivity() {
        val intent = Intent(context, NewsDetailsActivity::class.java)
        intent.putExtra(EXTRA_OBJECT, newsArticle as Serializable)
        startActivity(context, intent,null)
    }

    fun bind(newsArticle: NewsArticle) {
        this.newsArticle = newsArticle
        title.value = newsArticle.title
        description.value = newsArticle.description
        imageUrl.value = newsArticle.urlToImage
        dateView.value = newsArticle.publishedAt
        source.value = "this filed has been removed"
        author.value = newsArticle.author
        url.value = newsArticle.url
        content.value = newsArticle.content
    }

    fun getTitle():MutableLiveData<String> {
        return title
    }

    fun getDescription():MutableLiveData<String> {
        return description
    }

    fun getImageUrl():MutableLiveData<String> {
        return imageUrl;
    }

    fun getDateView():MutableLiveData<String> {
        return dateView
    }

    fun getSource():MutableLiveData<String> {
        return source
    }

    fun getAuthor():MutableLiveData<String> {
        return author
    }

    fun getUrl():MutableLiveData<String> {
        return url
    }

    fun getContent():MutableLiveData<String> {
        return content
    }
}