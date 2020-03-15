package com.techbots.latestnews.viewmodel

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import com.techbots.latestnews.view.ImageGenerateActivity

class HomepageVM(var context: Context) : BaseViewModel(){
    fun onClickGenerateDog() {
        startImageGenerateActivity()
    }

    private fun startImageGenerateActivity() {
        val intent = Intent(context, ImageGenerateActivity::class.java)
        startActivity(context, intent,null)
    }
}