package com.techbots.latestnews.network

import com.techbots.latestnews.db.NewsInfo
import retrofit2.http.GET
import retrofit2.http.Query
import io.reactivex.Observable
import retrofit2.Response

/**
 * This is an interface where we can define all server related queries with method type like @GET or @POST
 */
interface APIInterface {

    @GET("top-headlines")
    suspend fun getTopHeadLines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String,@Query("page") page: Int): Response<NewsInfo>

    @GET("top-headlines")
    suspend fun getTopHeadLinesByCatogery(
        @Query("category") category: String,
        @Query("apiKey") apiKey: String,@Query("page") page: Int): Response<NewsInfo>

    @GET("everything")
    suspend fun getEverything(
        @Query("q") country: String,
        @Query("apiKey") apiKey: String,@Query("page") page: Int): Response<NewsInfo>
}