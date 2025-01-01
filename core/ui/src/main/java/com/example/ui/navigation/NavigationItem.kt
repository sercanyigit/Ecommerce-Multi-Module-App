package com.example.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object Home : NavigationItem(
        route = "home",
        title = "Ana Sayfa",
        icon = Icons.Default.Home
    )
    
    data object Favorites : NavigationItem(
        route = "favorites",
        title = "Favoriler",
        icon = Icons.Default.Favorite
    )
    
    data object Notifications : NavigationItem(
        route = "notifications",
        title = "Bildirimler",
        icon = Icons.Default.Notifications
    )
    
    data object Profile : NavigationItem(
        route = "profile",
        title = "Profil",
        icon = Icons.Default.Person
    )
} 