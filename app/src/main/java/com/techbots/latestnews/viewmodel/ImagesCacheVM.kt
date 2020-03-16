package com.techbots.latestnews.viewmodel

import android.content.Context
import android.graphics.Bitmap
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.techbots.latestnews.utils.DiskCacheSingleTon
import com.techbots.latestnews.view.ImageCacheAdapter

class ImagesCacheVM(var context: Context): BaseViewModel() {
    var noImagesFound = MutableLiveData<Boolean>()
    var diskLruImageCache = DiskCacheSingleTon.getInstance(context)!!.diskLruImageCache
    var imageCacheAdapter = ImageCacheAdapter()
    private var listBitmap = ArrayList<Bitmap>(20)
    init {
        val file = diskLruImageCache.cacheFolder
        var fileNames = file.listFiles()
        for (fileName in fileNames)  {
            if (fileName != null) {
                diskLruImageCache.getBitmap(fileName.name.replaceFirst(".0", ""))?.let {
                    listBitmap.add(
                        it
                    )
                }
            }
        }

        if(listBitmap.isEmpty()) {
            noImagesFound.value = true
        } else {
            noImagesFound.value = false
            imageCacheAdapter.updateData(listBitmap)
        }
    }

    fun onClearCacheClick() {
        if(!noImagesFound.value!!) {
            noImagesFound.value = true
            diskLruImageCache.clearCache()
            imageCacheAdapter.clearData()
            DiskCacheSingleTon.getInstance(context)!!.clearCache()
        }
    }
}