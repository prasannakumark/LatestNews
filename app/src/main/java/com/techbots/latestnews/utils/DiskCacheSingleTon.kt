package com.techbots.latestnews.utils

import android.content.Context
import android.graphics.Bitmap

class DiskCacheSingleTon private constructor(context: Context) {
    val diskLruImageCache: DiskLruImageCache
    // Get max available VM memory, exceeding this amount will throw an
// OutOfMemory exception. Stored in kilobytes as LruCache takes an
// int in its constructor.
    var maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()
    // Use 1/8th of the available memory for this memory cache.
    var cacheSize = maxMemory

    companion object {
        private var mDiskCacheSingleTon: DiskCacheSingleTon? = null
        fun getInstance(context: Context): DiskCacheSingleTon? {
            if (mDiskCacheSingleTon == null) {
                synchronized(DiskLruImageCache::class) {
                    if (mDiskCacheSingleTon == null) {
                        mDiskCacheSingleTon =
                            DiskCacheSingleTon(context)
                    }
                }
            }
            return mDiskCacheSingleTon
        }
    }

    fun clearCache() {
        mDiskCacheSingleTon = null
    }

    init {
        diskLruImageCache =
            DiskLruImageCache(context, "test", cacheSize, Bitmap.CompressFormat.JPEG, 20)
    }
}