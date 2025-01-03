package com.sercan.ecommerce.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sercan.ecommerce.database.dao.ProductDao
import com.sercan.ecommerce.database.dao.CartDao
import com.sercan.ecommerce.database.entity.CartItemEntity
import com.sercan.ecommerce.database.entity.toEntity
import com.sercan.ecommerce.database.entity.toProduct
import com.sercan.ecommerce.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class FavoritesUiState(
    val favoriteProducts: List<Product> = emptyList(),
    val error: String? = null
)

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val productDao: ProductDao,
    private val cartDao: CartDao
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(FavoritesUiState())
    val uiState: StateFlow<FavoritesUiState> = productDao.getFavoriteProducts()
        .map { products -> _uiState.value.copy(favoriteProducts = products.map { it.toProduct() }) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = FavoritesUiState()
        )
    
    fun removeFromFavorites(product: Product) {
        viewModelScope.launch {
            productDao.deleteProduct(product.toEntity())
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
} 