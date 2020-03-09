package com.techbots.latestnews.datasource

import androidx.room.Database
import androidx.room.RoomDatabase

/***
 * This is main class for create database and tables base on @link{entities}
 * and also we can mention the version whenever database schema changed
 */
@Database(entities = [NewsArticle::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): NewsDAO
}