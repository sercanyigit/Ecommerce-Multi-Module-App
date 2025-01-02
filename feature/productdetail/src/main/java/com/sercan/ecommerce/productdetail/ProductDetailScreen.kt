package com.sercan.ecommerce.productdetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    viewModel: ProductDetailViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val product = uiState.product
    var selectedSize by remember { mutableStateOf<String?>(null) }
    var selectedColor by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        text = product?.name ?: "",
                        style = MaterialTheme.typography.titleLarge
                    ) 
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* TODO: Add to cart */ }) {
                        Badge {
                            Text(
                                text = "0",
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "Cart"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        if (product == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
            ) {
                AsyncImage(
                    model = product.imageUrl,
                    contentDescription = product.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = product.name,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "₺${product.price}",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Size",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        product.sizes.forEach { size ->
                            FilterChip(
                                selected = selectedSize == size,
                                onClick = { selectedSize = size },
                                label = { 
                                    Text(
                                        text = size,
                                        style = MaterialTheme.typography.labelMedium
                                    ) 
                                }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Color",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        product.colors.forEach { color ->
                            FilterChip(
                                selected = selectedColor == color,
                                onClick = { selectedColor = color },
                                label = { 
                                    Text(
                                        text = color,
                                        style = MaterialTheme.typography.labelMedium
                                    ) 
                                }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Description",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = product.description,
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = { /* TODO: Add to cart */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = "Add to Cart",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
} 