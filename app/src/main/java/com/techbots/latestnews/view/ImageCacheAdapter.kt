package com.techbots.latestnews.view

import android.graphics.Bitmap
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.techbots.latestnews.R
import com.techbots.latestnews.databinding.ItemViewBinding
import com.techbots.latestnews.viewmodel.ItemVM

class ImageCacheAdapter: RecyclerView.Adapter<ImageCacheAdapter.ViewHolder>() {
    private var imagesList = ArrayList<Bitmap>()

    fun updateData(bitmapList: List<Bitmap>) {
        Log.v("ImageCacheAdapter", "bitmapList " + bitmapList.size)
        this.imagesList.addAll(bitmapList);
        this.notifyDataSetChanged()
    }

    fun clearData() {
        this.imagesList.clear()
        this.notifyDataSetChanged()
    }

    class ViewHolder(private var itemsListBinding: ItemViewBinding) :
        RecyclerView.ViewHolder(itemsListBinding.root) {
        private val itemViewModel = ItemVM()

        fun bind(bitmap: Bitmap) {
            itemViewModel.bind(bitmap)
            itemsListBinding.viewModel = itemViewModel
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemViewBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
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
    }
}