package com.techbots.latestnews.utils

import android.content.Context
import android.os.Build
import android.os.Environment
import java.io.File

object Utils {
    const val IO_BUFFER_SIZE = 8 * 1024
    val isExternalStorageRemovable: Boolean
        get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            Environment.isExternalStorageRemovable()
        } else true

    fun getExternalCacheDir(context: Context): File? {
        if (hasExternalCacheDir()) {
            return context.externalCacheDir
        }
        // Before Froyo we need to construct the external cache dir ourselves
        val cacheDir = "/Android/data/" + context.packageName + "/cache/"
        return File(Environment.getExternalStorageDirectory().path + cacheDir)
    }

    fun hasExternalCacheDir(): Boolean {
        return true
    }
}