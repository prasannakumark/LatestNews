package com.techbots.latestnews.network

import com.techbots.latestnews.db.NewsInfo
import retrofit2.http.GET
import retrofit2.http.Query
import io.reactivex.Observable

/**
 * This is an interface where we can define all server related queries with method type like @GET or @POST
 */
interface APIInterface {

    @GET("top-headlines")
    fun getTopHeadLines(
        @Query("country") country: String, @Query("category") category: String,
        @Query("apiKey") apiKey: String,@Query("page") page: Int): Observable<NewsInfo>
}