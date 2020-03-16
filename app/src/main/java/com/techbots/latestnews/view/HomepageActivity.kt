package com.techbots.latestnews.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.techbots.latestnews.R
import com.techbots.latestnews.databinding.ActivityHomepageBinding
import com.techbots.latestnews.di.ViewModelFactory
import com.techbots.latestnews.viewmodel.HomepageVM

class HomepageActivity : AppCompatActivity() {
    private lateinit var homepageVM: HomepageVM
    private lateinit var dataBiding: ActivityHomepageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        dataBiding = DataBindingUtil.setContentView(this,
            R.layout.activity_homepage
        )
        homepageVM = ViewModelProviders.of(this,ViewModelFactory(this)).get(HomepageVM::class.java)
        dataBiding.viewModel = homepageVM

        dataBiding.generateDogs.setOnClickListener(View.OnClickListener {
            homepageVM.onClickGenerateDog()
        })

        dataBiding.generatedDogs.setOnClickListener({
            homepageVM.onClickMyRecentGenerateDogs()
        })
    }
}
