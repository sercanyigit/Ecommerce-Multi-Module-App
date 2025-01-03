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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
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
    val description: String,
    val imageUrl: String,
    val isSelected: Boolean = false
)

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String,
    val category: String,
    val isFavorite: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    onProductClick: (Int) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.error) {
        uiState.error?.let {
            snackbarHostState.showSnackbar(it)
        }
    }

    val banners = listOf(
        Banner(
            1,
            "Yeni Sezon",
            "Sonbahar koleksiyonu",
            "%20 İndirim",
            "https://images.unsplash.com/photo-1607083206869-4c7672e72a8a"
        ),
        Banner(
            2,
            "Kış Fırsatları",
            "Kış sezonu indirimleri",
            "%40 İndirim",
            "https://images.unsplash.com/photo-1483985988355-763728e1935b"
        ),
        Banner(
            3,
            "Özel Teklif",
            "İlk alışverişinize özel",
            "%30 İndirim",
            "https://images.unsplash.com/photo-1540959733332-eab4deabeeaf"
        )
    )

    val categories = listOf(
        Category(
            1,
            "Tümü",
            "Tüm Ürünler",
            "https://images.unsplash.com/photo-1441986300917-64674bd600d8",
            uiState.selectedCategory == "Tümü"
        ),
        Category(
            2,
            "Erkek",
            "Erkek Giyim & Aksesuar",
            "https://images.unsplash.com/photo-1490367532201-b9bc1dc483f6",
            uiState.selectedCategory == "Erkek"
        ),
        Category(
            3,
            "Kadın",
            "Kadın Giyim & Aksesuar",
            "https://images.unsplash.com/photo-1469334031218-e382a71b716b",
            uiState.selectedCategory == "Kadın"
        ),
        Category(
            4,
            "Ayakkabı",
            "Spor & Günlük Ayakkabılar",
            "https://images.unsplash.com/photo-1549298916-b41d501d3772",
            uiState.selectedCategory == "Ayakkabı"
        ),
        Category(
            5,
            "Çanta",
            "Çanta & Cüzdan",
            "https://images.unsplash.com/photo-1591561954557-26941169b49e",
            uiState.selectedCategory == "Çanta"
        ),
        Category(
            6,
            "Aksesuar",
            "Saat & Takı & Gözlük",
            "https://images.unsplash.com/photo-1523170335258-f5ed11844a49",
            uiState.selectedCategory == "Aksesuar"
        ),
        Category(
            7,
            "Elektronik",
            "Telefon & Bilgisayar & Tablet",
            "https://images.unsplash.com/photo-1468495244123-6c6c332eeece",
            uiState.selectedCategory == "Elektronik"
        ),
        Category(
            8,
            "Ev & Yaşam",
            "Mobilya & Dekorasyon",
            "https://images.unsplash.com/photo-1493663284031-b7e3aefcae8e",
            uiState.selectedCategory == "Ev & Yaşam"
        ),
        Category(
            9,
            "Spor",
            "Spor Giyim & Ekipman",
            "https://images.unsplash.com/photo-1571019614242-c5c5dee9f50b",
            uiState.selectedCategory == "Spor"
        ),
        Category(
            10,
            "Kozmetik",
            "Parfüm & Makyaj",
            "https://images.unsplash.com/photo-1596462502278-27bfdc403348",
            uiState.selectedCategory == "Kozmetik"
        )
    )

    val pagerState = rememberPagerState(pageCount = { banners.size })

    // Otomatik geçiş için LaunchedEffect
    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
            pagerState.animateScrollToPage(
                page = nextPage,
                animationSpec = tween(
                    durationMillis = 800,
                    easing = CubicBezierEasing(0.4f, 0.0f, 0.2f, 1.0f)
                )
            )
        }
    }

    // Animasyon için visible state
    var isVisible by remember { mutableStateOf(false) }
    
    // Ekran açıldığında animasyonu başlat
    LaunchedEffect(Unit) {
        delay(150)
        isVisible = true
    }

    // Kategori değişiminde animasyonu tetikle
    LaunchedEffect(uiState.selectedCategory) {
        isVisible = false
        delay(150) // Kısa bir gecikme
        isVisible = true
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
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
                    .padding(horizontal = 16.dp)
            ) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxWidth(),
                    pageSpacing = 0.dp,
                    contentPadding = PaddingValues(0.dp)
                ) { page ->
                    val pageOffset = (
                        (pagerState.currentPage - page) + pagerState
                            .currentPageOffsetFraction
                    ).absoluteValue

                    Card(
                        modifier = Modifier
                            .graphicsLayer {
                                val scale = lerp(
                                    start = 0.9f,
                                    stop = 1f,
                                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                )
                                scaleX = scale
                                scaleY = scale

                                alpha = lerp(
                                    start = 0.6f,
                                    stop = 1f,
                                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                )

                                // Yumuşak yatay kayma efekti
                                translationX = size.width * pageOffset * if (page > pagerState.currentPage) 0.5f else -0.5f
                                
                                // Derinlik ve perspektif efekti
                                rotationY = lerp(
                                    start = 0f,
                                    stop = 40f,
                                    fraction = pageOffset
                                ) * if (page > pagerState.currentPage) 1f else -1f
                                
                                cameraDistance = 8f * density
                            }
                            .fillMaxWidth()
                            .height(180.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = (8.dp * (1f - pageOffset)).coerceIn(0.dp, 8.dp)
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .graphicsLayer {
                                    // İçeriğin ters rotasyonu ile yazıların okunabilir kalması
                                    rotationY = lerp(
                                        start = 0f,
                                        stop = -40f,
                                        fraction = pageOffset
                                    ) * if (page > pagerState.currentPage) 1f else -1f
                                }
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .graphicsLayer {
                                        // Yazılar için ekstra animasyon
                                        alpha = lerp(
                                            start = 0.8f,
                                            stop = 1f,
                                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                        )
                                        translationX = -size.width * 0.1f * pageOffset
                                    }
                            ) {
                                Text(
                                    text = banners[page].title,
                                    style = MaterialTheme.typography.headlineMedium,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                                Text(
                                    text = banners[page].description,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                                Text(
                                    text = banners[page].discount,
                                    style = MaterialTheme.typography.titleLarge,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Button(
                                    onClick = { },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = MaterialTheme.colorScheme.primary
                                    )
                                ) {
                                    Text("Alışverişe Başla")
                                }
                            }
                            AsyncImage(
                                model = banners[page].imageUrl,
                                contentDescription = null,
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .size(120.dp)
                                    .weight(1f)
                                    .graphicsLayer {
                                        // Resim için özel animasyon
                                        alpha = lerp(
                                            start = 0.8f,
                                            stop = 1f,
                                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                        )
                                        translationX = size.width * 0.2f * pageOffset
                                        scaleX = lerp(
                                            start = 0.8f,
                                            stop = 1f,
                                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                        )
                                        scaleY = lerp(
                                            start = 0.8f,
                                            stop = 1f,
                                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                        )
                                    }
                            )
                        }
                    }
                }

                // Banner Indicators
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                        .align(Alignment.BottomCenter)
                ) {
                    Row(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        repeat(pagerState.pageCount) { iteration ->
                            Box(
                                modifier = Modifier
                                    .width(if (pagerState.currentPage == iteration) 24.dp else 8.dp)
                                    .height(8.dp)
                                    .clip(CircleShape)
                                    .background(
                                        if (pagerState.currentPage == iteration)
                                            MaterialTheme.colorScheme.primary
                                        else
                                            MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                                    )
                                    .animateContentSize()
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Categories
            Text(
                text = "Kategoriler",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            
            Spacer(modifier = Modifier.height(8.dp))

            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(categories) { category ->
                    Card(
                        onClick = { viewModel.filterProducts(category.name) },
                        modifier = Modifier
                            .width(140.dp)
                            .height(180.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (category.isSelected)
                                MaterialTheme.colorScheme.primaryContainer
                            else
                                MaterialTheme.colorScheme.surface
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = if (category.isSelected) 8.dp else 2.dp
                        )
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            AsyncImage(
                                model = category.imageUrl,
                                contentDescription = category.name,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(100.dp)
                                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                            )
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                            ) {
                                Text(
                                    text = category.name,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Text(
                                    text = category.description,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Products with animation
            AnimatedVisibility(
                visible = isVisible,
                enter = slideInVertically(
                    initialOffsetY = { it / 10 }, // Daha az yükseklik
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeIn(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                ),
                exit = slideOutVertically(
                    targetOffsetY = { it / 10 },
                    animationSpec = tween(
                        durationMillis = 200,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeOut(
                    animationSpec = tween(
                        durationMillis = 200,
                        easing = FastOutSlowInEasing
                    )
                )
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(
                        items = uiState.filteredProducts,
                        key = { it.id }
                    ) { product ->
                        ProductCard(
                            product = product,
                            onProductClick = { onProductClick(product.id) },
                            onFavoriteClick = { viewModel.toggleFavorite(product) },
                            onAddToCartClick = { viewModel.addToCart(product) },
                            modifier = Modifier.animateItemPlacement(
                                animationSpec = tween(
                                    durationMillis = 300,
                                    easing = FastOutSlowInEasing
                                )
                            )
                        )
                    }
                }
            }
        }
    }
}