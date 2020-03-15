package com.techbots.latestnews.di.components

import com.techbots.latestnews.di.modules.DataRepositoryModule
import com.techbots.latestnews.di.modules.NetworkModule
import com.techbots.latestnews.viewmodel.HomepageVM
import com.techbots.latestnews.viewmodel.ImageGenerateVM
import com.techbots.latestnews.viewmodel.ImagesCacheVM
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = arrayOf(DataRepositoryModule::class))
interface FactoryComponent {
    fun inject(homepageVM: HomepageVM)
    fun inject(imageGenerateVM: ImageGenerateVM)
    fun inject(imagesCacheVM: ImagesCacheVM)
    @Component.Builder
    interface Builder {
        fun build(): FactoryComponent

        fun networkModule(networkModule: NetworkModule): Builder
        fun dataRepositoryModule(dataRepositoryModule:DataRepositoryModule): Builder
    }

}