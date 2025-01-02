package com.sercan.ecommerce.home

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.sercan.ecommerce.ui.components.ProductCard
import kotlinx.coroutines.delay
import kotlin.math.absoluteValue

private fun lerp(start: Float, stop: Float, fraction: Float): Float {
    return start + fraction * (stop - start)
}

data class Banner(
    val id: Int,
    val title: String,
    val description: String,
    val discount: String,
    val imageUrl: String
)

data class Category(
    val id: Int,
    val name: String,
    val isSelected: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onProductClick: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    val banners = listOf(
        Banner(
            1, 
            "20%", 
            "Discount", 
            "on your first purchase",
            "https://example.com/shoe1.jpg"
        ),
        Banner(
            2,
            "40%",
            "Discount",
            "on your first purchase",
            "https://example.com/shoe1.jpg"
        ),
        Banner(
            3,
            "20%",
            "Discount",
            "on your first purchase",
            "https://example.com/shoe1.jpg"
        )
    )

    val categories = listOf(
        Category(1, "All", true),
        Category(2, "Running"),
        Category(3, "Sneakers"),
        Category(4, "Formal"),
        Category(5, "Casual")
    )

    val pagerState = rememberPagerState(pageCount = { banners.size })

    // Otomatik geçiş için LaunchedEffect
    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
            pagerState.animateScrollToPage(nextPage)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    AsyncImage(
                        model = R.drawable.nike_logo,
                        contentDescription = "Nike Logo",
                        modifier = Modifier.height(24.dp)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "Cart"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Banner
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxWidth()
                ) { page ->
                    val pageOffset = (
                        (pagerState.currentPage - page) + pagerState
                            .currentPageOffsetFraction
                    ).absoluteValue

                    Card(
                        modifier = Modifier
                            .graphicsLayer {
                                val scale = lerp(
                                    start = 0.85f,
                                    stop = 1f,
                                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                )
                                scaleX = scale
                                scaleY = scale

                                alpha = lerp(
                                    start = 0.5f,
                                    stop = 1f,
                                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                )

                                rotationY = lerp(
                                    start = 30f,
                                    stop = 0f,
                                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                )
                            }
                            .fillMaxWidth()
                            .height(160.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFF5F5F5)
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        ) {
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = banners[page].title,
                                    style = MaterialTheme.typography.displaySmall,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = banners[page].description,
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Text(
                                    text = banners[page].discount,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Button(
                                    onClick = { },
                                    shape = RoundedCornerShape(8.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.Black
                                    )
                                ) {
                                    Text(
                                        text = "Shop now",
                                        style = MaterialTheme.typography.labelLarge
                                    )
                                }
                            }
                            AsyncImage(
                                model = banners[page].imageUrl,
                                contentDescription = "Promo Shoe",
                                contentScale = ContentScale.Fit,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }

                // Banner Indicators
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                        .align(Alignment.BottomCenter)
                ) {
                    Row(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        repeat(pagerState.pageCount) { iteration ->
                            Box(
                                modifier = Modifier
                                    .width(if (pagerState.currentPage == iteration) 20.dp else 6.dp)
                                    .height(6.dp)
                                    .clip(CircleShape)
                                    .background(
                                        if (pagerState.currentPage == iteration) 
                                            Color.Black 
                                        else 
                                            Color.LightGray.copy(alpha = 0.5f)
                                    )
                                    .animateContentSize(
                                        animationSpec = spring(
                                            dampingRatio = Spring.DampingRatioMediumBouncy,
                                            stiffness = Spring.StiffnessLow
                                        )
                                    )
                            )
                        }
                    }
                }
            }

            // Categories
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(categories) { category ->
                    FilterChip(
                        selected = category.isSelected,
                        onClick = { viewModel.filterProducts(category.name) },
                        label = {
                            Text(
                                text = category.name,
                                style = MaterialTheme.typography.labelMedium
                            )
                        },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = Color.Black,
                            selectedLabelColor = Color.White
                        ),
                        shape = RoundedCornerShape(24.dp)
                    )
                }
            }

            // Products Grid
            AnimatedVisibility(
                visible = true,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(
                        items = uiState.products,
                        key = { it.id }
                    ) { product ->
                        ProductCard(
                            product = product,
                            onProductClick = { onProductClick(it.id) },
                            onFavoriteClick = { viewModel.toggleFavorite(it) },
                            modifier = Modifier.animateItemPlacement(
                                animationSpec = spring(
                                    dampingRatio = Spring.DampingRatioMediumBouncy,
                                    stiffness = Spring.StiffnessLow
                                )
                            )
                        )
                    }
                }
            }
        }
    }
}