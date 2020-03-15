package com.techbots.latestnews.network

import com.techbots.latestnews.datasource.ImageInfo
import retrofit2.http.GET
import io.reactivex.Observable

/**
 * This is an interface where we can define all server related queries with method type like @GET or @POST
 */
interface APIInterface {

    @GET("random")
    fun getDogImage(): Observable<ImageInfo>
}