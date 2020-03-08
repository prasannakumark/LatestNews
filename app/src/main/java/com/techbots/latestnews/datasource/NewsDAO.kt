package com.techbots.latestnews.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.techbots.latestnews.datasource.NewsArticle

@Dao
interface NewsDAO {
    @get:Query("SELECT * FROM NewsArticle")
    val all: List<NewsArticle>

    @Query("DELETE FROM NewsArticle")
    fun deleteAll()

    @Transaction
    @Insert
    fun insertAll(articles: List<NewsArticle>)
}