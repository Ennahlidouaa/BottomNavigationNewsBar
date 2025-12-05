package com.example.bottomnavigationnewsbar.screens

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.bottomnavigationnewsbar.components.ArticleItem
import com.example.bottomnavigationnewsbar.models.Article
import com.example.bottomnavigationnewsbar.navigation.ScreenNews
import com.example.bottomnavigationnewsbar.viewmodels.NewsViewModel

@Composable
fun SavedNewsScreen(
    navController: NavController,
    newsViewModel: NewsViewModel
) {
    // 🔥 Observer les favoris depuis Room
    val savedArticles by newsViewModel.savedArticles.observeAsState()

    if (savedArticles == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }

    // Liste des articles sauvegardés
    LazyColumn {
        items(savedArticles!!) { saved ->

            // Convertir SavedArticle -> Article (pour réutiliser ArticleItem)
            val article = Article(
                title = saved.title,
                description = saved.description,
                urlToImage = saved.urlToImage,
                url = saved.url,
                publishedAt = saved.publishedAt,
                content = null,
                author = null,
                source = null
            )

            ArticleItem(article = article) {

                // 🔥 Préparer ArticleScreen pour la sauvegarde
                newsViewModel.currentArticle = article

                val encodedUrl = Uri.encode(saved.url)

                navController.navigate(
                    "${ScreenNews.Article.route}/$encodedUrl"
                )
            }
        }
    }
}
