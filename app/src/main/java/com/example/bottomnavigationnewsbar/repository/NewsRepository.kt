package com.example.bottomnavigationnewsbar.repository

import androidx.lifecycle.LiveData
import com.example.bottomnavigationnewsbar.SavedArticle
import com.example.bottomnavigationnewsbar.network.RetrofitInstance

// NewsRepository .kt
class NewsRepository(private val db: com.example.bottomnavigationnewsbar.NewsDatabase) {
    suspend fun getBreakingNews(countryCode: String) =
        RetrofitInstance.retrofitService.getBreakingNews(countryCode)

    suspend fun getSearchNews(searchQuery: String) =
        RetrofitInstance.retrofitService.getSearchNews(searchQuery)
    suspend fun saveArticle(article: SavedArticle) = db.articleDao().insertArticle(article)

    fun getSavedArticles(): LiveData<List<SavedArticle>> = db.articleDao().getSavedArticles()

    suspend fun deleteArticle(article: SavedArticle) = db.articleDao().deleteArticle(article)
}