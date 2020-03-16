package com.techbots.latestnews.viewmodel

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import com.techbots.latestnews.view.ImageGenerateActivity
import com.techbots.latestnews.view.ImagesCacheActivity

class HomepageVM(var context: Context) : BaseViewModel(){
    fun onClickGenerateDog() {
        startImageGenerateActivity()
    }

    fun onClickMyRecentGenerateDogs() {
        startImageCacheAtivity()
    }

    private fun startImageCacheAtivity() {
        val intent = Intent(context, ImagesCacheActivity::class.java)
        startActivity(context, intent,null)
    }

    private fun startImageGenerateActivity() {
        val intent = Intent(context, ImageGenerateActivity::class.java)
        startActivity(context, intent,null)
    }
}