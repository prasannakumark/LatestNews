package com.techbots.latestnews.di.modules

import com.techbots.latestnews.network.APIInterface
import com.techbots.latestnews.utils.BASE_URL
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import org.jetbrains.annotations.NotNull
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@Suppress("unused")
object NetworkModule {

    /**
    * Provides the Post service implementation.
    * @param retrofit the Retrofit object used to instantiate the service
    * @return the Post service implementation.
    */
    @Provides
    @JvmStatic
    internal fun providePostApi(@NotNull retrofit: Retrofit): APIInterface {
        return retrofit.create(APIInterface::class.java)
    }

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @JvmStatic
    @NotNull
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }
}