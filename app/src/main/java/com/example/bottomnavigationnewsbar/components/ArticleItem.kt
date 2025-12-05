package com.example.bottomnavigationnewsbar.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.bottomnavigationnewsbar.models.Article

@Composable
fun ArticleItem(article: Article, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() } // clic géré ici
            .padding(8.dp)
    ) {
        AsyncImage(
            model = article.urlToImage ,
            contentDescription = article.title ,
            modifier = Modifier
                .fillMaxWidth()
                .height (180.dp)
        )
        Text (
            text = article.title ?: " Sans titre ",
            fontWeight = FontWeight.Bold ,
            modifier = Modifier .padding(top = 8.dp)
        )
        Text (
            text = article.description ?: "",
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
        Divider(modifier = Modifier .padding(vertical = 8.dp))
    }
}
