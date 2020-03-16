package com.techbots.latestnews.viewmodel

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.techbots.latestnews.R
import com.techbots.latestnews.datasource.ImageInfo
import com.techbots.latestnews.db.DataRepository
import com.techbots.latestnews.utils.DiskCacheSingleTon
import com.techbots.latestnews.utils.isOnline
import com.techbots.latestnews.view.ImageGenerateActivity
import java.net.URL
import javax.inject.Inject
import kotlin.random.Random

class ImageGenerateVM(var context: Context): BaseViewModel(), DataRepository.UICallBacks  {
    val loadingVisibility:MutableLiveData<Int> = MutableLiveData()

    val imageUrl = MutableLiveData<Bitmap>()
    private lateinit var callBacks: CallBacks
    private var diskLruImageCache = DiskCacheSingleTon.getInstance(context)!!.diskLruImageCache

    fun setCallBacks(callBacks: CallBacks) {
        this.callBacks = callBacks
    }
    interface CallBacks {
        fun networkError()
    }

    init {
        loadingVisibility.value = View.GONE
    }

    @Inject
    lateinit var dataRepository: DataRepository

    fun getImage() {
        if(isOnline(context)) {
            loadingVisibility.value = View.VISIBLE
            dataRepository.makeServerRequest(this)
        }else {
            callBacks.networkError()
        }
    }

    override fun onRetrieveDateSuccess(messageInfo: ImageInfo) {
        Log.v(ImageGenerateVM::class.simpleName, "onRetrieveDateSuccess ${messageInfo.message}")
        val imageGenerateActivity:ImageGenerateActivity = context as ImageGenerateActivity

        Thread {
            val url = URL(messageInfo.message)
            val bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            val randomValues = Random.nextInt(0, 100)
            diskLruImageCache.put(randomValues.toString(), bitmap)

            imageGenerateActivity.runOnUiThread({
                loadingVisibility.value = View.GONE
                imageUrl.value = bitmap
            })
        }.start()
    }

    override fun onRetrieveDataError() {
        Toast.makeText(context,context.getString(R.string.something_went_wrong),Toast.LENGTH_LONG).show()
    }
}