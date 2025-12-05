package com.example.bottomnavigationnewsbar.network

import com.example.bottomnavigationnewsbar.models.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "0b7efb8511a54f069e98ed40cad1ec92"

interface NewsApiService {

    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country") countryCode: String = "us",
        @Query("apiKey") apiKey: String = API_KEY
    ): Response<NewsResponse>

    @GET("v2/everything")
    suspend fun getSearchNews(
        @Query("q") searchQuery: String,
        @Query("apiKey") apiKey: String = API_KEY
    ): Response<NewsResponse>
}
