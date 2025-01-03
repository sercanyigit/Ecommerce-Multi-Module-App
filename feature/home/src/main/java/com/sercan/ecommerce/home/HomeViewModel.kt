package com.sercan.ecommerce.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sercan.ecommerce.database.dao.ProductDao
import com.sercan.ecommerce.database.dao.CartDao
import com.sercan.ecommerce.database.entity.CartItemEntity
import com.sercan.ecommerce.database.mockdata.MockData
import com.sercan.ecommerce.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val products: List<Product> = emptyList(),
    val filteredProducts: List<Product> = emptyList(),
    val selectedCategory: String = "Tümü",
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productDao: ProductDao,
    private val cartDao: CartDao
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
        viewModelScope.launch {
            try {
                productDao.insertProducts(MockData.sampleProducts)
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

    fun addToCart(product: Product) {
        viewModelScope.launch {
            try {
                cartDao.insertCartItem(
                    CartItemEntity(
                        id = 0,
                        productId = product.id,
                        name = product.name,
                        price = product.price,
                        imageUrl = product.imageUrl,
                        quantity = 1,
                        selectedSize = product.sizes.firstOrNull(),
                        selectedColor = product.colors.firstOrNull()
                    )
                )
                // Başarılı mesajı
                _uiState.update { it.copy(error = "${product.name} sepete eklendi") }
                clearError()
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    private fun clearError() {
        viewModelScope.launch {
            delay(2000) // 2 saniye sonra error'u temizle
            _uiState.update { it.copy(error = null) }
        }
    }

    fun searchProducts(query: String) {
        _uiState.update { currentState ->
            val searchResults = if (query.length >= 2) {
                currentState.products.filter { product ->
                    product.name.contains(query, ignoreCase = true) ||
                    product.description.contains(query, ignoreCase = true) ||
                    product.category.contains(query, ignoreCase = true)
                }
            } else {
                filterProductsByCategory(currentState.products, currentState.selectedCategory)
            }
            
            currentState.copy(
                searchQuery = query,
                filteredProducts = searchResults
            )
        }
    }
} 