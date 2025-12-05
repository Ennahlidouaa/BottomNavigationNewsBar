package com.example.bottomnavigationnewsbar

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: SavedArticle)

    @Delete
    suspend fun deleteArticle(article: SavedArticle)

    @Query("SELECT * FROM saved_articles")
    fun getSavedArticles(): LiveData<List<SavedArticle>>
}
