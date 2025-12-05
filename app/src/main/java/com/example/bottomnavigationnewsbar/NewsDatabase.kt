package com.example.bottomnavigationnewsbar

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SavedArticle::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao

    companion object {
        @Volatile private var INSTANCE: NewsDatabase? = null

        fun getDatabase(context: Context): NewsDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    NewsDatabase::class.java,
                    "news_db"
                ).build().also { INSTANCE = it }
            }
    }
}
