package com.sercan.ecommerce.common.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(
    val route: String,
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badge: Int = 0
) {
    object Home : NavigationItem(
        route = "home",
        title = "Anasayfa",
        selectedIcon = Icons.Filled.Storefront,
        unselectedIcon = Icons.Outlined.Storefront
    )

    data class Cart(val itemCount: Int = 0) : NavigationItem(
        route = ROUTE,
        title = "Sepetim",
        selectedIcon = Icons.Filled.ShoppingBag,
        unselectedIcon = Icons.Outlined.ShoppingBag,
        badge = itemCount
    ) {
        companion object {
            const val ROUTE = "cart"
        }
    }

    object Favorites : NavigationItem(
        route = "favorites",
        title = "Favoriler",
        selectedIcon = Icons.Filled.Favorite,
        unselectedIcon = Icons.Outlined.FavoriteBorder
    )

    object Notifications : NavigationItem(
        route = "notifications",
        title = "Bildirimler",
        selectedIcon = Icons.Filled.Notifications,
        unselectedIcon = Icons.Outlined.Notifications
    )

    object Profile : NavigationItem(
        route = "profile",
        title = "Profil",
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person
    )
} 