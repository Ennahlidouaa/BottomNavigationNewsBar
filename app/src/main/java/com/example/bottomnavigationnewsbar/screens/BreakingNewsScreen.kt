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
import com.example.bottomnavigationnewsbar.navigation.ScreenNews
import com.example.bottomnavigationnewsbar.viewmodels.NewsViewModel

@Composable
fun BreakingNewsScreen(
    navController: NavController,
    viewModel: NewsViewModel
) {
    val newsResponse by viewModel.breakingNews.observeAsState()

    if (newsResponse != null) {

        LazyColumn {
            items(newsResponse!!.articles) { article ->

                ArticleItem(article = article) {

                    // 🔥 Stocker l’article courant pour ArticleScreen + Room
                    viewModel.currentArticle = article

                    // 🔥 Encoder correctement l’URL
                    val encodedUrl = Uri.encode(article.url)

                    // 🔥 Route propre (sans espaces !)
                    navController.navigate(
                        "${ScreenNews.Article.route}/$encodedUrl"
                    )
                }
            }
        }

    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}
