package com.techbots.latestnews.datasource

import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class NewsArticle(val author:String? = null,
                       @PrimaryKey val title:String,
                       val description:String? = null,
                       val url:String? = null,
                       val urlToImage:String? = null,
                       val publishedAt:String? = null,
                       val content:String? = null) : Serializable {
}