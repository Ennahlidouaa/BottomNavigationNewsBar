package com.example.bottomnavigationnewsbar.navigation

sealed class ScreenNews(val route: String) {
    object BreakingNews : ScreenNews("breaking_news")
    object AllNews : ScreenNews("all_news")
    object SavedNews : ScreenNews("saved_news")
    object Article : ScreenNews("article")
}