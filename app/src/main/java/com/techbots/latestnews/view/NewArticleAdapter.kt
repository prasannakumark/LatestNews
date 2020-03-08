package com.techbots.latestnews.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.techbots.latestnews.R
import com.techbots.latestnews.databinding.ItemArticleBinding
import com.techbots.latestnews.datasource.NewsArticle
import com.techbots.latestnews.viewmodel.ArticleViewModel

class NewArticleAdapter : RecyclerView.Adapter<NewArticleAdapter.ViewHolder>() {
    private var newsArticleList: ArrayList<NewsArticle> = ArrayList<NewsArticle>()

    fun updateArticleList(newsArticleList: List<NewsArticle>) {
        Log.v("NewArticleAdapter", "updateArticleList " + newsArticleList.size)
        this.newsArticleList.addAll(newsArticleList);
        this.notifyDataSetChanged()
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

    override fun getItemCount(): Int {
        return newsArticleList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(newsArticleList.get(position))
    }
}