package com.techbots.latestnews.di.modules

import com.techbots.latestnews.datasource.WebServices
import com.techbots.latestnews.db.DataRepository
import com.techbots.latestnews.network.APIInterface
import dagger.Module
import dagger.Provides
import org.jetbrains.annotations.NotNull

/**
 * This class will for get @constructor DataRepository object by providing the their dependencies
 */
@Module(includes = arrayOf(NetworkModule::class))
object DataRepositoryModule {
    @Provides
    @JvmStatic
    internal fun provideWebService(@NotNull apiInterface: APIInterface) : WebServices {
        return WebServices(apiInterface)
    }

    @Provides
    @JvmStatic
    internal fun provideDataRepositoryModule(webServices: WebServices) : DataRepository {
        return DataRepository(webServices)
    }
}