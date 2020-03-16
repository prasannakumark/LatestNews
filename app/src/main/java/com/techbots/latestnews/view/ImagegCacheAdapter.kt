package com.techbots.latestnews.view

import android.graphics.Bitmap
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.techbots.latestnews.R

class ImagegCacheAdapter{ /*RecyclerView.Adapter<ImagegCacheAdapter.ViewHolder>() {
    private var imagesList: List<String> = ArrayList<String>()

    fun updateArticleList(newsArticleList: List<Bitmap>) {
        Log.v("NewArticleAdapter", "updateArticleList " + newsArticleList.size)
        //this.imagesList.add(newsArticleList);
        this.notifyDataSetChanged()
    }

    class ViewHolder(private var itemsListBinding: ItemArticleBinding) :
        RecyclerView.ViewHolder(itemsListBinding.root) {
        private val articleViewModel = ArticleViewModel(itemsListBinding.frameView.context)

        fun bind(newsArticle: String) {
            articleViewModel.bind(newsArticle)
            itemsListBinding.viewModel = articleViewModel
            itemsListBinding.frameView.setOnClickListener(articleViewModel.onClickListener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemArticleBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.item_view, parent, false)
        return ViewHolder(
            binding
        )
    }

    override fun getItemCount(): Int {
        return imagesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(imagesList.get(position))
    }*/
}