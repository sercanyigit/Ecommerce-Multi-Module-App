package com.sercan.ecommerce.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.SearchOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.sercan.ecommerce.database.mockdata.MockData
import com.sercan.ecommerce.ui.components.ProductCard
import kotlinx.coroutines.delay
import kotlin.math.absoluteValue

private fun lerp(start: Float, stop: Float, fraction: Float): Float {
    return start + fraction * (stop - start)
}

data class Category(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String,
    val isSelected: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class,
    ExperimentalLayoutApi::class
)
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

    val pagerState = rememberPagerState(pageCount = { MockData.banners.size })

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

    var searchExpanded by remember { mutableStateOf(false) }
    
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = {
                    SearchBar(
                        query = uiState.searchQuery,
                        onQueryChange = viewModel::searchProducts,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 8.dp)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(top = 10.dp)
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
                                    text = MockData.banners[page].title,
                                    style = MaterialTheme.typography.headlineMedium,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                                Text(
                                    text = MockData.banners[page].description,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                                Text(
                                    text = MockData.banners[page].discount,
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
                                model = MockData.banners[page].imageUrl,
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

            val categoryPagerState = rememberPagerState(
                initialPage = 0,
                pageCount = { (categories.size + 2) / 3 }
            )

            // Otomatik geçiş için LaunchedEffect
            LaunchedEffect(Unit) {
                while (true) {
                    delay(3000)
                    val nextPage = (categoryPagerState.currentPage + 1) % categoryPagerState.pageCount
                    categoryPagerState.animateScrollToPage(
                        page = nextPage,
                        animationSpec = tween(
                            durationMillis = 500,
                            easing = FastOutSlowInEasing
                        )
                    )
                }
            }

            HorizontalPager(
                state = categoryPagerState,
                contentPadding = PaddingValues(horizontal = 16.dp),
                pageSpacing = 16.dp
            ) { page ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    val startIndex = page * 3
                    for (i in startIndex until minOf(startIndex + 3, categories.size)) {
                        val category = categories[i]
                        Card(
                            onClick = { viewModel.filterProducts(category.name) },
                            modifier = Modifier
                                .width(110.dp)
                                .height(140.dp)
                                .graphicsLayer {
                                    val pageOffset = (
                                        (categoryPagerState.currentPage - page) + categoryPagerState
                                            .currentPageOffsetFraction
                                    ).absoluteValue

                                    alpha = lerp(
                                        start = 0.5f,
                                        stop = 1f,
                                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                    )

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
                                },
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
                                        .height(80.dp)
                                        .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                                )
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp)
                                ) {
                                    Text(
                                        text = category.name,
                                        style = MaterialTheme.typography.titleSmall,
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
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(categoryPagerState.pageCount) { iteration ->
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(
                                if (categoryPagerState.currentPage == iteration)
                                    MaterialTheme.colorScheme.primary
                                else
                                    MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                            )
                    )
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
                if (uiState.searchQuery.length >= 2 && uiState.filteredProducts.isEmpty()) {
                    // Ürün bulunamadı tasarımı
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.SearchOff,
                            contentDescription = null,
                            modifier = Modifier
                                .size(80.dp)
                                .padding(bottom = 12.dp),
                            tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
                        )
                        
                        Text(
                            text = "Ürün Bulunamadı",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        
                        Spacer(modifier = Modifier.height(4.dp))
                        
                        Text(
                            text = "\"${uiState.searchQuery}\" araması için sonuç yok",
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                        )
                    }
                } else {
                    // Mevcut ürün listesi
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
}

@Composable
private fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(24.dp),
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
        )
    ) {
        TextField(
            value = query,
            onValueChange = onQueryChange,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 48.dp),
            placeholder = {
                Text(
                    text = "Ürün, kategori veya marka ara...",
                    style = MaterialTheme.typography.bodyLarge
                )
            },
            textStyle = MaterialTheme.typography.bodyLarge,
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            trailingIcon = {
                if (query.isNotEmpty()) {
                    IconButton(
                        onClick = { onQueryChange("") },
                        modifier = Modifier.padding(end = 4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Temizle",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    // Klavyeyi kapat
                    defaultKeyboardAction(ImeAction.Search)
                }
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = MaterialTheme.colorScheme.primary
            )
        )
    }
}