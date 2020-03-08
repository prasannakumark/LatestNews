package com.techbots.latestnews.di.components

import com.techbots.latestnews.di.modules.DataRepositoryModule
import com.techbots.latestnews.di.modules.NetworkModule
import com.techbots.latestnews.viewmodel.ArticleViewModel
import com.techbots.latestnews.viewmodel.HomePageViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = arrayOf(DataRepositoryModule::class))
interface HomePageComponent {
    fun inject(homePageViewModel: HomePageViewModel)
    fun inject(articleViewModel: ArticleViewModel)
    @Component.Builder
    interface Builder {
        fun build(): HomePageComponent

        fun networkModule(networkModule: NetworkModule): Builder
        fun dataRepositoryModule(dataRepositoryModule:DataRepositoryModule): Builder
    }

}