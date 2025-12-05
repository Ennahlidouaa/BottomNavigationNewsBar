package com.example.bottomnavigationnewsbar.screens

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bottomnavigationnewsbar.components.ArticleItem
import com.example.bottomnavigationnewsbar.navigation.ScreenNews
import com.example.bottomnavigationnewsbar.viewmodels.NewsViewModel

@Composable
fun AllNewsScreen(
    navController: NavController,
    viewModel: NewsViewModel
) {
    val query = remember { mutableStateOf("android") }
    val searchResult = viewModel.searchNews.observeAsState()

    Column {

        // Champ de recherche
        TextField(
            value = query.value,
            onValueChange = {
                query.value = it
                viewModel.getSearchNews(it)
            },
            placeholder = { Text("Rechercher...") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        // Liste des résultats
        LazyColumn {
            searchResult.value?.articles?.let { articles ->
                items(articles) { article ->

                    ArticleItem(article) {

                        // 🔥 Sauvegarde de l'article courant (pour ArticleScreen)
                        viewModel.currentArticle = article

                        val encodedUrl = Uri.encode(article.url)

                        navController.navigate(
                            "${ScreenNews.Article.route}/$encodedUrl"
                        )
                    }
                }
            }
        }
    }
}
