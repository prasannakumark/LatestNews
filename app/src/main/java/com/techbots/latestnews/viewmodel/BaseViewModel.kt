package com.techbots.latestnews.viewmodel

import androidx.lifecycle.ViewModel
import com.techbots.latestnews.di.components.DaggerHomePageComponent
import com.techbots.latestnews.di.components.HomePageComponent
import com.techbots.latestnews.di.modules.DataRepositoryModule
import com.techbots.latestnews.di.modules.NetworkModule

abstract class BaseViewModel:ViewModel(){
    private val injector: HomePageComponent = DaggerHomePageComponent
        .builder()
        .networkModule(NetworkModule)
        .dataRepositoryModule(DataRepositoryModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is HomePageViewModel -> injector.inject(this)
            is ArticleViewModel -> injector.inject(this)
        }
    }
}