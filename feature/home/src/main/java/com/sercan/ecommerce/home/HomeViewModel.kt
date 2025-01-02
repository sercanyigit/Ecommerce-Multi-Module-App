package com.sercan.ecommerce.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sercan.ecommerce.database.dao.ProductDao
import com.sercan.ecommerce.database.entity.ProductEntity
import com.sercan.ecommerce.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val products: List<Product> = emptyList(),
    val filteredProducts: List<Product> = emptyList(),
    val selectedCategory: String = "Tümü",
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productDao: ProductDao
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState(isLoading = true))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadProducts()
        observeProducts()
    }

    private fun observeProducts() {
        viewModelScope.launch {
            productDao.getProducts()
                .map { entities -> entities.map { it.toProduct() } }
                .catch { e ->
                    _uiState.update { it.copy(error = e.message, isLoading = false) }
                }
                .collect { products ->
                    _uiState.update { currentState ->
                        currentState.copy(
                            products = products,
                            filteredProducts = filterProductsByCategory(products, currentState.selectedCategory),
                            isLoading = false
                        )
                    }
                }
        }
    }

    private fun filterProductsByCategory(products: List<Product>, category: String): List<Product> {
        return if (category == "Tümü") {
            products
        } else {
            products.filter { it.category == category }
        }
    }

    fun filterProducts(category: String) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedCategory = category,
                filteredProducts = filterProductsByCategory(currentState.products, category)
            )
        }
    }

    private fun loadProducts() {
        if (_uiState.value.products.isNotEmpty()) return

        viewModelScope.launch {
            try {
                val sampleProducts = listOf(
                    ProductEntity(
                        id = 1,
                        name = "Nike Air Max",
                        price = 2499.99,
                        imageUrl = "https://example.com/nike-air-max.jpg",
                        description = "Nike Air Max spor ayakkabı, maksimum konfor ve şık tasarım.",
                        category = "Ayakkabı",
                        sizes = listOf("40", "41", "42", "43", "44"),
                        colors = listOf("Siyah", "Beyaz", "Kırmızı")
                    ),
                    ProductEntity(
                        id = 2,
                        name = "Adidas Superstar",
                        price = 1999.99,
                        imageUrl = "https://example.com/adidas-superstar.jpg",
                        description = "Adidas Superstar, klasik tasarımıyla her tarza uygun.",
                        category = "Ayakkabı",
                        sizes = listOf("39", "40", "41", "42", "43"),
                        colors = listOf("Beyaz", "Siyah", "Mavi")
                    ),
                    ProductEntity(
                        id = 3,
                        name = "Puma RS-X",
                        price = 1799.99,
                        imageUrl = "https://example.com/puma-rsx.jpg",
                        description = "Puma RS-X, retro tasarımıyla modern bir görünüm.",
                        category = "Ayakkabı",
                        sizes = listOf("40", "41", "42", "43", "44"),
                        colors = listOf("Gri", "Siyah", "Yeşil")
                    ),
                    ProductEntity(
                        id = 4,
                        name = "New Balance 574",
                        price = 2299.99,
                        imageUrl = "https://example.com/new-balance-574.jpg",
                        description = "New Balance 574, rahatlık ve stil bir arada.",
                        category = "Ayakkabı",
                        sizes = listOf("39", "40", "41", "42", "43"),
                        colors = listOf("Lacivert", "Gri", "Bordo")
                    )
                )
                productDao.insertProducts(sampleProducts)
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message, isLoading = false) }
            }
        }
    }

    fun toggleFavorite(product: Product) {
        viewModelScope.launch {
            try {
                productDao.toggleFavorite(product.id, !product.isFavorite)
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }
} 