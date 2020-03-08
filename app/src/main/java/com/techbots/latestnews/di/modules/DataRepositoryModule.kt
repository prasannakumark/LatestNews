package com.techbots.latestnews.di.modules

import com.techbots.latestnews.db.DataRepository
import com.techbots.latestnews.network.APIInterface
import dagger.Module
import dagger.Provides
import org.jetbrains.annotations.NotNull

@Module(includes = arrayOf(NetworkModule::class))
object DataRepositoryModule {
    @Provides
    @JvmStatic
    internal fun provideDataRepositoryModule(@NotNull apiInterface: APIInterface) : DataRepository {
        return DataRepository(apiInterface)
    }
}