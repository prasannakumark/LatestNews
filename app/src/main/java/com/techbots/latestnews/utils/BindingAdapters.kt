package com.techbots.latestnews.utils

/**
 * This file we can define all custom databinding related work
 */

import android.graphics.Bitmap
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.techbots.latestnews.R
import com.techbots.latestnews.utils.extension.getParentActivity

@BindingAdapter("bind:urlToImage")
fun setUrlToImage(view: ImageView, text: MutableLiveData<Bitmap>?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && text != null) {
        Log.v("setUrlToImage ", "Image url " + text.value)
        text.observe(parentActivity, Observer { view.setImageBitmap(text.value)})
    }
}
