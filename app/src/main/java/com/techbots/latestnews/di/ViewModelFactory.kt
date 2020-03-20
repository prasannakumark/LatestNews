package com.techbots.latestnews.di

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.techbots.latestnews.datasource.AppDatabase
import com.techbots.latestnews.viewmodel.ArticleViewModel
import com.techbots.latestnews.viewmodel.NewsArticleViewModel

/**
 * This is class for get exact view model based on the type of activity
 */
class ViewModelFactory(private val activity: FragmentActivity): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsArticleViewModel::class.java)) {
            val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "NewsArticle").build()
            @Suppress("UNCHECKED_CAST")
            return NewsArticleViewModel(activity, db.articleDao()) as T
        } else if (modelClass.isAssignableFrom(ArticleViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ArticleViewModel(activity) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}