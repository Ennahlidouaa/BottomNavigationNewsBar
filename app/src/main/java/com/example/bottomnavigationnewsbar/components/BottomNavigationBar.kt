package com.example.bottomnavigationnewsbar.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MailOutline

import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource

import androidx.navigation.NavController
import com.example.bottomnavigationnewsbar.R

import com.example.bottomnavigationnewsbar.data.NavigationItem
import com.example.bottomnavigationnewsbar.navigation.ScreenNews




    @Composable
    fun BottomNavigationBar(
        navController: NavController
    ) {
        val selectedNavigationIndex = rememberSaveable {
            mutableIntStateOf(0)
        }

        val navigationItems = listOf(
            NavigationItem(
                title =stringResource(R.string.breaking_news_txt),
                icon = Icons.Default.Warning,
                route = ScreenNews.BreakingNews.route
            ),
            NavigationItem(
                title = stringResource(R.string.saved_news_txt),
                icon = Icons.Default.Favorite,
                route = ScreenNews.SavedNews.route
            ),
            NavigationItem(
                title = stringResource(R.string.search_news_txt),
                icon = Icons.Default.List,
                route = ScreenNews.AllNews.route
            )
        )

        NavigationBar(
            containerColor = Color.White
        ) {
            navigationItems.forEachIndexed { index, item ->
                NavigationBarItem(
                    selected = selectedNavigationIndex.intValue == index,
                    onClick = {
                        selectedNavigationIndex.intValue = index
                        navController.navigate(item.route)
                    },
                    icon = {
                        Icon(imageVector = item.icon, contentDescription = item.title)
                    },
                    label = {
                        Text(
                            item.title,
                            color = if (index == selectedNavigationIndex.intValue)
                                Color.Black
                            else Color.Gray
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.surface,
                        indicatorColor = MaterialTheme.colorScheme.primary
                    )

                )
            }
        }
    }


