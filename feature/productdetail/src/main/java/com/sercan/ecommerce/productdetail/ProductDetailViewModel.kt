package com.sercan.ecommerce.productdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sercan.ecommerce.database.dao.ProductDao
import com.sercan.ecommerce.model.Product
import com.sercan.ecommerce.productdetail.domain.model.CartItem
import com.sercan.ecommerce.productdetail.domain.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ProductDetailUiState(
    val product: Product? = null,
    val selectedQuantity: Int = 1,
    val selectedSize: String? = null,
    val selectedColor: String? = null,
    val cartItemCount: Int = 0,
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val productDao: ProductDao,
    private val cartRepository: CartRepository
) : ViewModel() {

    private val productId: Int = checkNotNull(savedStateHandle["productId"])
    private val _uiState = MutableStateFlow(ProductDetailUiState(isLoading = true))
    val uiState: StateFlow<ProductDetailUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                productDao.getProduct(productId)?.let { productEntity ->
                    _uiState.update { it.copy(
                        product = Product(
                            id = productEntity.id,
                            name = productEntity.name,
                            price = productEntity.price,
                            imageUrl = productEntity.imageUrl,
                            description = productEntity.description,
                            isFavorite = productEntity.isFavorite,
                            category = productEntity.category,
                            sizes = productEntity.sizes.orEmpty(),
                            colors = productEntity.colors.orEmpty()
                        ),
                        isLoading = false
                    )}
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message, isLoading = false) }
            }
        }

        // Cart item count'u dinle
        viewModelScope.launch {
            cartRepository.getCartItemCount().collect { count ->
                _uiState.update { it.copy(cartItemCount = count) }
            }
        }
    }

    fun onQuantityChange(quantity: Int) {
        _uiState.update { it.copy(selectedQuantity = quantity) }
    }

    fun onSizeSelect(size: String) {
        _uiState.update { it.copy(selectedSize = size) }
    }

    fun onColorSelect(color: String) {
        _uiState.update { it.copy(selectedColor = color) }
    }

    fun addToCart() {
        val currentState = _uiState.value
        val product = currentState.product ?: return

        if (currentState.selectedSize == null || currentState.selectedColor == null) {
            _uiState.update { it.copy(error = "Lütfen beden ve renk seçiniz") }
            return
        }

        viewModelScope.launch {
            try {
                val cartItem = CartItem(
                    id = 0, // Room otomatik id atayacak
                    productId = product.id,
                    name = product.name,
                    price = product.price,
                    imageUrl = product.imageUrl,
                    quantity = currentState.selectedQuantity,
                    selectedSize = currentState.selectedSize,
                    selectedColor = currentState.selectedColor
                )
                cartRepository.addToCart(cartItem)
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    fun toggleFavorite() {
        viewModelScope.launch {
            try {
                _uiState.value.product?.let { product ->
                    productDao.toggleFavorite(product.id, !product.isFavorite)
                    // UI'ı güncelle
                    _uiState.update { it.copy(
                        product = it.product?.copy(isFavorite = !product.isFavorite)
                    )}
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }
} 