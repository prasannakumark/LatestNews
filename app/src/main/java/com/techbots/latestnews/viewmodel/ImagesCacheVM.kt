package com.techbots.latestnews.viewmodel

import android.content.Context
import android.graphics.Bitmap
import com.techbots.latestnews.utils.DiskLruImageCache

class ImagesCacheVM(var context: Context): BaseViewModel() {
    // Get max available VM memory, exceeding this amount will throw an
    // OutOfMemory exception. Stored in kilobytes as LruCache takes an
    // int in its constructor.
    private val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()

    private val cacheSize = maxMemory
    private lateinit var diskLruImageCache:DiskLruImageCache

    init {
        diskLruImageCache = DiskLruImageCache(context,"test",cacheSize,Bitmap.CompressFormat.JPEG,20)
        var file = diskLruImageCache.cacheFolder
        var fileName = file.listFiles()
        //imageUrl.value = diskLruImageCache.getBitmap(fileName[1].name.replaceFirst(".0", ""))
    }

}