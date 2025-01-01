package com.example.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ui.components.ProductCard
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.graphics.graphicsLayer
import kotlin.math.absoluteValue

data class Banner(
    val id: Int,
    val title: String,
    val description: String,
    val discount: String
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
    
    // Örnek banner verileri
    val banners = listOf(
        Banner(1, "İndirim", "İlk alışverişinize özel", "20% Discount"),
        Banner(2, "Yeni Sezon", "Yaz koleksiyonu", "New Collection"),
        Banner(3, "Fırsat", "Seçili ürünlerde", "30% Off")
    )
    
    // Örnek kategori verileri
    val categories = listOf(
        Category(1, "All"),
        Category(2, "Running"),
        Category(3, "Sneakers"),
        Category(4, "Formal"),
        Category(5, "Casual")
    )
    
    val pagerState = rememberPagerState(pageCount = { banners.size })
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Nike Store") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Banner Pager
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxSize()
                ) { page ->
                    val banner = banners[page]
                    val pageOffset = (
                        (pagerState.currentPage - page) + pagerState
                            .currentPageOffsetFraction
                    ).absoluteValue
                    
                    Card(
                        modifier = Modifier
                            .graphicsLayer {
                                lerp(
                                    start = 0.85f,
                                    stop = 1f,
                                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                ).also { scale ->
                                    scaleX = scale
                                    scaleY = scale
                                }
                                alpha = lerp(
                                    start = 0.5f,
                                    stop = 1f,
                                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                )
                            }
                            .fillMaxSize()
                            .padding(16.dp)
                            .clip(RoundedCornerShape(16.dp)),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        ) {
                            Column {
                                Text(
                                    text = banner.title,
                                    style = MaterialTheme.typography.headlineMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = banner.description,
                                    style = MaterialTheme.typography.bodyLarge
                                )
                                Text(
                                    text = banner.discount,
                                    style = MaterialTheme.typography.headlineSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                            Button(
                                onClick = { },
                                modifier = Modifier.align(Alignment.BottomStart)
                            ) {
                                Text("Shop now")
                            }
                        }
                    }
                }
                
                // Pager İndikatörü
                Row(
                    Modifier
                        .height(50.dp)
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(pagerState.pageCount) { iteration ->
                        val color = if (pagerState.currentPage == iteration) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.surfaceVariant
                        }
                        Box(
                            modifier = Modifier
                                .padding(2.dp)
                                .clip(CircleShape)
                                .background(color)
                                .size(8.dp)
                        )
                    }
                }
            }
            
            // Categories
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(categories) { category ->
                    FilterChip(
                        selected = category.name == uiState.selectedCategory,
                        onClick = { viewModel.filterProducts(category.name) },
                        label = { Text(category.name) }
                    )
                }
            }
            
            // Products Grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(uiState.products) { product ->
                    ProductCard(
                        product = product,
                        onProductClick = { onProductClick(it.id) },
                        onFavoriteClick = { viewModel.toggleFavorite(it) }
                    )
                }
            }
        }
    }
}

private fun lerp(start: Float, stop: Float, fraction: Float): Float {
    return start + fraction * (stop - start)
} 