package com.techbots.latestnews.view

import androidx.recyclerview.widget.DiffUtil
import com.techbots.latestnews.datasource.NewsArticle

class DiffUtilCallBack : DiffUtil.ItemCallback<NewsArticle>() {
    override fun areItemsTheSame(oldItem: NewsArticle, newItem: NewsArticle): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: NewsArticle, newItem: NewsArticle): Boolean {
        return oldItem.title == newItem.title
    }
}
