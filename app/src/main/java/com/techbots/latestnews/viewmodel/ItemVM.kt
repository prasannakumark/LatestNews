package com.techbots.latestnews.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData

class ItemVM: BaseViewModel() {
    val imageUrl = MutableLiveData<Bitmap>()

    fun bind(bitmap: Bitmap) {
        imageUrl.value = bitmap
    }
}