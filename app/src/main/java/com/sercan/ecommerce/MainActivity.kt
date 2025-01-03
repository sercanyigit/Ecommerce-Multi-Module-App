package com.sercan.ecommerce

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.sercan.ecommerce.addtocard.AddToCardScreen
import com.sercan.ecommerce.database.dao.CartDao
import com.sercan.ecommerce.favorites.FavoritesScreen
import com.sercan.ecommerce.home.HomeScreen
import com.sercan.ecommerce.notifications.NotificationsScreen
import com.sercan.ecommerce.productdetail.ProductDetailScreen
import com.sercan.ecommerce.profile.ProfileScreen
import com.sercan.ecommerce.common.navigation.NavigationItem
import com.sercan.ecommerce.common.navigation.DeepLinks
import com.sercan.ecommerce.onboarding.OnboardingScreen
import com.sercan.ecommerce.ui.theme.EcommerceTheme
import com.sercan.ecommerce.ui.theme.ThemeManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var cartDao: CartDao

    @Inject
    lateinit var themeManager: ThemeManager

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()

        setContent {
            EcommerceTheme(themeManager = themeManager) {
                val systemUiController = rememberSystemUiController()
                val darkTheme = isSystemInDarkTheme()

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
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentRoute = navBackStackEntry?.destination?.route
                            
                            if (currentRoute != "onboarding") {
                                NavigationBar {
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
                        }
                    ) { innerPadding ->
                        NavHost(
                            navController = navController,
                            startDestination = "onboarding",
                            modifier = Modifier.padding(innerPadding),
                            enterTransition = {
                                slideIntoContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                                    animationSpec = tween(300)
                                ) + fadeIn(animationSpec = tween(300))
                            },
                            exitTransition = {
                                slideOutOfContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                                    animationSpec = tween(300)
                                ) + fadeOut(animationSpec = tween(300))
                            },
                            popEnterTransition = {
                                slideIntoContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                                    animationSpec = tween(300)
                                ) + fadeIn(animationSpec = tween(300))
                            },
                            popExitTransition = {
                                slideOutOfContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                                    animationSpec = tween(300)
                                ) + fadeOut(animationSpec = tween(300))
                            }
                        ) {
                            composable(route = "onboarding") {
                                OnboardingScreen(
                                    onOnboardingComplete = {
                                        navController.navigate(NavigationItem.Home.route) {
                                            popUpTo("onboarding") { inclusive = true }
                                        }
                                    }
                                )
                            }
                            composable(
                                route = NavigationItem.Home.route,
                                deepLinks = listOf(
                                    navDeepLink { uriPattern = DeepLinks.fromCode(DeepLinks.Home.CODE) }
                                )
                            ) {
                                HomeScreen(
                                    onProductClick = { productId ->
                                        navController.navigate(DeepLinks.ProductDetail.createRoute(productId))
                                    }
                                )
                            }
                            composable(
                                route = NavigationItem.Cart.ROUTE,
                                deepLinks = listOf(
                                    navDeepLink { uriPattern = DeepLinks.fromCode(DeepLinks.Cart.CODE) }
                                )
                            ) {
                                AddToCardScreen(
                                    onBackClick = { navController.popBackStack() }
                                )
                            }
                            composable(
                                route = NavigationItem.Favorites.route,
                                deepLinks = listOf(
                                    navDeepLink { uriPattern = DeepLinks.fromCode(DeepLinks.Favorites.CODE) }
                                )
                            ) {
                                FavoritesScreen(
                                    onProductClick = { productId ->
                                        navController.navigate(DeepLinks.ProductDetail.createRoute(productId))
                                    }
                                )
                            }
                            composable(
                                route = NavigationItem.Profile.route,
                                deepLinks = listOf(
                                    navDeepLink { uriPattern = DeepLinks.fromCode(DeepLinks.Profile.CODE) }
                                )
                            ) {
                                ProfileScreen()
                            }
                            composable(
                                route = NavigationItem.Notifications.route,
                                deepLinks = listOf(
                                    navDeepLink { uriPattern = DeepLinks.fromCode(DeepLinks.Notifications.CODE) }
                                )
                            ) {
                                NotificationsScreen()
                            }
                            composable(
                                route = "product_detail/{productId}",
                                arguments = listOf(
                                    navArgument("productId") { type = NavType.IntType }
                                ),
                                deepLinks = listOf(
                                    navDeepLink { 
                                        uriPattern = DeepLinks.fromCode("product_detail/{productId}")
                                    }
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