package com.techbots.latestnews.db

import com.techbots.latestnews.datasource.NewsArticle
import java.io.Serializable

data class NewsInfo(var status:String,
                    var totalResult:Long,
                    var articles:List<NewsArticle>) : Serializable{
}