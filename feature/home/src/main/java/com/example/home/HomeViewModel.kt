package com.example.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.database.dao.ProductDao
import com.example.database.entity.toEntity
import com.example.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val products: List<Product> = emptyList(),
    val selectedCategory: String = "All"
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productDao: ProductDao
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState
    
    private val allProducts = listOf(
        Product(
            id = 1,
            name = "Air Max 97",
            price = 20.99,
            imageUrl = "https://via.placeholder.com/200",
            description = "Nike Air Max 97",
            category = "Running"
        ),
        Product(
            id = 2,
            name = "React Presto",
            price = 25.99,
            imageUrl = "https://via.placeholder.com/200",
            description = "Nike React Presto",
            category = "Sneakers"
        ),
        Product(
            id = 3,
            name = "Air Force 1",
            price = 30.99,
            imageUrl = "https://via.placeholder.com/200",
            description = "Nike Air Force 1",
            category = "Casual"
        ),
        Product(
            id = 4,
            name = "Oxford Classic",
            price = 45.99,
            imageUrl = "https://via.placeholder.com/200",
            description = "Classic Oxford Shoes",
            category = "Formal"
        ),
        Product(
            id = 5,
            name = "Air Zoom Pegasus",
            price = 28.99,
            imageUrl = "https://via.placeholder.com/200",
            description = "Nike Air Zoom Pegasus",
            category = "Running"
        ),
        Product(
            id = 6,
            name = "Classic Loafer",
            price = 35.99,
            imageUrl = "https://via.placeholder.com/200",
            description = "Classic Loafer Shoes",
            category = "Formal"
        )
    )
    
    init {
        loadProducts()
    }
    
    private fun loadProducts() {
        filterProducts("All")
    }
    
    fun filterProducts(category: String) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedCategory = category,
                products = if (category == "All") {
                    allProducts
                } else {
                    allProducts.filter { it.category == category }
                }
            )
        }
    }
    
    fun toggleFavorite(product: Product) {
        viewModelScope.launch {
            val updatedProduct = product.copy(isFavorite = !product.isFavorite)
            if (updatedProduct.isFavorite) {
                productDao.insertProduct(updatedProduct.toEntity())
            } else {
                productDao.deleteProduct(product.toEntity())
            }
            _uiState.update {
                it.copy(
                    products = it.products.map { p ->
                        if (p.id == product.id) updatedProduct else p
                    }
                )
            }
        }
    }
} 