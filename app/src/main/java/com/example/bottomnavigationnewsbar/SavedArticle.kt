package com.example.bottomnavigationnewsbar

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_articles")
data class SavedArticle(
    @PrimaryKey val url: String,
    val title: String?,
    val description: String?,
    val urlToImage: String?,
    val publishedAt: String?
)
