package com.techbots.latestnews.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.techbots.latestnews.R
import com.techbots.latestnews.databinding.ItemArticleBinding
import com.techbots.latestnews.datasource.NewsArticle
import com.techbots.latestnews.viewmodel.ArticleViewModel

class NewArticleAdapter : PagedListAdapter<NewsArticle, NewArticleAdapter.ViewHolder>(DiffUtilCallBack()) {

    fun updateArticleList(newsArticleList: List<NewsArticle>) {
        Log.v("NewArticleAdapter", "updateArticleList " + newsArticleList.size)
    }

    class ViewHolder(private var itemsListBinding: ItemArticleBinding) :
        RecyclerView.ViewHolder(itemsListBinding.root) {
        private val articleViewModel = ArticleViewModel(itemsListBinding.frameView.context)

        fun bind(newsArticle: NewsArticle) {
            articleViewModel.bind(newsArticle)
            itemsListBinding.viewModel = articleViewModel
            itemsListBinding.frameView.setOnClickListener(articleViewModel.onClickListener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemArticleBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.item_article, parent, false)
        return ViewHolder(
            binding
        )
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}