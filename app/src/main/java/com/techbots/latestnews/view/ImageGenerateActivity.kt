package com.techbots.latestnews.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.techbots.latestnews.R
import com.techbots.latestnews.databinding.ActivityGenerateDogsBinding
import com.techbots.latestnews.di.ViewModelFactory
import com.techbots.latestnews.viewmodel.ImageGenerateVM

class ImageGenerateActivity : AppCompatActivity() {
    private lateinit var dataBinding: ActivityGenerateDogsBinding
    private lateinit var viewModel: ImageGenerateVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_generate_dogs
        )
        viewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(ImageGenerateVM::class.java)
        dataBinding.viewModel = viewModel
        dataBinding.button.setOnClickListener(View.OnClickListener {
            viewModel.getImage()
        })
    }
}
