package com.example.bottomnavigationnewsbar.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bottomnavigationnewsbar.SavedArticle
import com.example.bottomnavigationnewsbar.models.NewsResponse
import com.example.bottomnavigationnewsbar.models.Article// ✅ CORRECT
import com.example.bottomnavigationnewsbar.repository.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel(
    private val newsRepository: NewsRepository
) : ViewModel() {

    // ---------------------- API NEWS ---------------------------------

    val breakingNews: MutableLiveData<NewsResponse> = MutableLiveData()

    private val _searchNews = MutableLiveData<NewsResponse>()
    val searchNews: LiveData<NewsResponse> = _searchNews

    init {
        getBreakingNews("us")
    }

    private fun getBreakingNews(countryCode: String) = viewModelScope.launch {
        try {
            val response = newsRepository.getBreakingNews(countryCode)
            breakingNews.postValue(response.body())
        } catch (e: Exception) { }
    }

    fun getSearchNews(query: String) {
        viewModelScope.launch {
            try {
                val response = newsRepository.getSearchNews(query)
                if (response.isSuccessful) {
                    _searchNews.postValue(response.body())
                }
            } catch (e: Exception) { }
        }
    }

    // ---------------------- FAVORIS ROOM ------------------------------

    // Article actuellement affiché dans ArticleScreen
    var currentArticle: Article? = null

    // Favoris stockés dans Room
    val savedArticles: LiveData<List<SavedArticle>> =
        newsRepository.getSavedArticles()

    // Ajouter aux favoris
    fun saveFavorite(article: Article) {
        val saved = SavedArticle(
            url = article.url ?: "",
            title = article.title,
            description = article.description,
            urlToImage = article.urlToImage,
            publishedAt = article.publishedAt
        )

        viewModelScope.launch {
            newsRepository.saveArticle(saved)
        }
    }

    // Supprimer un favori
    fun deleteFavorite(article: SavedArticle) {
        viewModelScope.launch {
            newsRepository.deleteArticle(article)
        }
    }
}
