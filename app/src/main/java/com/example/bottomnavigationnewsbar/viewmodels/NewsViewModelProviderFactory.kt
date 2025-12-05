package com.example.bottomnavigationnewsbar.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bottomnavigationnewsbar.NewsDatabase
import com.example.bottomnavigationnewsbar.repository.NewsRepository

class NewsViewModelProviderFactory(
    private val app: Application
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            val newsRepository = NewsRepository(NewsDatabase.getDatabase(app))
            return NewsViewModel(newsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}