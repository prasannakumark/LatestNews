package com.techbots.latestnews.viewmodel

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.techbots.latestnews.datasource.ImageInfo
import com.techbots.latestnews.db.DataRepository
import com.techbots.latestnews.utils.DiskLruImageCache
import com.techbots.latestnews.view.ImageGenerateActivity
import java.net.URL
import javax.inject.Inject
import kotlin.random.Random

class ImageGenerateVM(var context: Context): BaseViewModel(), DataRepository.UICallBacks  {
    val imageUrl = MutableLiveData<Bitmap>()
    // Get max available VM memory, exceeding this amount will throw an
    // OutOfMemory exception. Stored in kilobytes as LruCache takes an
    // int in its constructor.
    val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()

    // Use 1/8th of the available memory for this memory cache.
    val cacheSize = maxMemory

    var diskLruImageCache = DiskLruImageCache(context,"test",cacheSize,Bitmap.CompressFormat.JPEG,20)
    @Inject
    lateinit var dataRepository: DataRepository

    fun getImage() {
        dataRepository.makeServerRequest(this)
    }

    override fun onRetrieveData() {
    }

    override fun onRetrieveDataFinish() {
    }

    override fun onRetrieveDateSuccess(messageInfo: ImageInfo) {
        Log.v(ImageGenerateVM::class.simpleName, "onRetrieveDateSuccess ${messageInfo.message}")
        //try {
        val imageGenerateActivity:ImageGenerateActivity = context as ImageGenerateActivity

        Thread {
            val url = URL(messageInfo.message)
            val bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            val randomValues = Random.nextInt(0, 100)

            diskLruImageCache.put(randomValues.toString(), bitmap)

            //addBitmapToMemoryCache(messageInfo.message, )
            //bitmap = getBitmapFromMemCache(messageInfo.message)
            imageGenerateActivity.runOnUiThread({
                imageUrl.value = bitmap
            })
        }.start()
    }

    override fun onRetrieveDataError() {
    }
}