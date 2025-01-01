package com.example.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.database.dao.ProductDao
import com.example.database.entity.toEntity
import com.example.database.entity.toProduct
import com.example.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class FavoritesUiState(
    val favoriteProducts: List<Product> = emptyList()
)

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val productDao: ProductDao
) : ViewModel() {
    
    val uiState: StateFlow<FavoritesUiState> = productDao.getFavoriteProducts()
        .map { products -> FavoritesUiState(products.map { it.toProduct() }) }
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
} 