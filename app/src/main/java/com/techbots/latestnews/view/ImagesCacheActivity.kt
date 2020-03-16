package com.techbots.latestnews.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.techbots.latestnews.R
import com.techbots.latestnews.databinding.ActivityImagesCacheBinding
import com.techbots.latestnews.databinding.ItemViewBinding
import com.techbots.latestnews.di.ViewModelFactory
import com.techbots.latestnews.viewmodel.ImagesCacheVM

class ImagesCacheActivity : AppCompatActivity() {
    private lateinit var dataBinding: ActivityImagesCacheBinding
    private lateinit var viewModel: ImagesCacheVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_images_cache
        )
        viewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(ImagesCacheVM::class.java)
        dataBinding.viewModel = viewModel
        dataBinding.cacheRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        dataBinding.btnClearDogs.setOnClickListener(View.OnClickListener {
            viewModel.onClearCacheClick()
        })
    }
}
