package com.techbots.latestnews.viewmodel

import androidx.lifecycle.ViewModel
import com.techbots.latestnews.di.components.DaggerFactoryComponent
import com.techbots.latestnews.di.components.FactoryComponent
import com.techbots.latestnews.di.modules.DataRepositoryModule
import com.techbots.latestnews.di.modules.NetworkModule

abstract class BaseViewModel:ViewModel(){
    private val injector: FactoryComponent = DaggerFactoryComponent
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
            is HomepageVM -> injector.inject(this)
            is ImageGenerateVM -> injector.inject(this)
            is ImagesCacheVM -> injector.inject(this)
        }
    }
}