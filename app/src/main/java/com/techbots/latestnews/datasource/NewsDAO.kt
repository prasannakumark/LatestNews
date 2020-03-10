package com.techbots.latestnews.datasource

import androidx.room.*

/**
 * All kind of database queries will be done here.
 * like Insert, delete and get data.
 */
@Dao
interface NewsDAO {
    @get:Query("SELECT * FROM NewsArticle")
    val all: List<NewsArticle>

    @Query("DELETE FROM NewsArticle")
    fun deleteAll()

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(articles: List<NewsArticle>)
}