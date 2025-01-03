package com.sercan.ecommerce

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.sercan.ecommerce.addtocard.AddToCardScreen
import com.sercan.ecommerce.database.dao.CartDao
import com.sercan.ecommerce.favorites.FavoritesScreen
import com.sercan.ecommerce.home.HomeScreen
import com.sercan.ecommerce.notifications.NotificationsScreen
import com.sercan.ecommerce.productdetail.ProductDetailScreen
import com.sercan.ecommerce.profile.ProfileScreen
import com.sercan.ecommerce.common.navigation.NavigationItem
import com.sercan.ecommerce.ui.theme.EcommerceTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var cartDao: CartDao

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()

        setContent {
            EcommerceTheme {
                val systemUiController = rememberSystemUiController()
                val darkTheme = isSystemInDarkTheme()
                val surfaceColor = MaterialTheme.colorScheme.surface
                
                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = Color.Transparent,
                        darkIcons = !darkTheme
                    )
                }

                val navController = rememberNavController()
                val cartItemCount by cartDao.getCartItemCount()
                    .collectAsState(initial = 0)

                val navigationItems = listOf(
                    NavigationItem.Home,
                    NavigationItem.Cart(cartItemCount),
                    NavigationItem.Favorites,
                    NavigationItem.Notifications,
                    NavigationItem.Profile
                )

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        bottomBar = {
                            NavigationBar {
                                val navBackStackEntry by navController.currentBackStackEntryAsState()
                                val currentRoute = navBackStackEntry?.destination?.route

                                navigationItems.forEach { item ->
                                    NavigationBarItem(
                                        icon = {
                                            BadgedBox(
                                                badge = {
                                                    if (item is NavigationItem.Cart && item.badge > 0) {
                                                        Badge { Text(item.badge.toString()) }
                                                    }
                                                }
                                            ) {
                                                Icon(
                                                    imageVector = if (currentRoute == item.route) {
                                                        item.selectedIcon
                                                    } else item.unselectedIcon,
                                                    contentDescription = item.title
                                                )
                                            }
                                        },
                                        label = { Text(item.title) },
                                        selected = currentRoute == item.route,
                                        onClick = {
                                            navController.navigate(item.route) {
                                                popUpTo(navController.graph.startDestinationId)
                                                launchSingleTop = true
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    ) { innerPadding ->
                        NavHost(
                            navController = navController,
                            startDestination = NavigationItem.Home.route,
                            modifier = Modifier.padding(innerPadding)
                        ) {
                            composable(NavigationItem.Home.route) {
                                HomeScreen(
                                    onProductClick = { productId ->
                                        navController.navigate("product_detail/$productId")
                                    }
                                )
                            }
                            composable(NavigationItem.Cart.ROUTE) {
                                AddToCardScreen(
                                    onBackClick = { navController.popBackStack() }
                                )
                            }
                            composable(NavigationItem.Favorites.route) {
                                FavoritesScreen(
                                    onProductClick = { productId ->
                                        navController.navigate("product_detail/$productId")
                                    }
                                )
                            }
                            composable(NavigationItem.Profile.route) {
                                ProfileScreen()
                            }
                            composable(NavigationItem.Notifications.route) {
                                NotificationsScreen()
                            }
                            composable(
                                route = "product_detail/{productId}",
                                arguments = listOf(
                                    navArgument("productId") { type = NavType.IntType }
                                )
                            ) {
                                ProductDetailScreen(
                                    onBackClick = { navController.popBackStack() },
                                    onCartClick = { navController.navigate(NavigationItem.Cart.ROUTE) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
} 