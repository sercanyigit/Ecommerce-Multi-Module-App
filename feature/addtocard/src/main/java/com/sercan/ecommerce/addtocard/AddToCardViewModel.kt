package com.sercan.ecommerce.addtocard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sercan.ecommerce.database.dao.CartDao
import com.sercan.ecommerce.database.entity.CartItemEntity
import com.sercan.ecommerce.model.CartItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddToCardViewModel @Inject constructor(
    private val cartDao: CartDao
) : ViewModel() {

    private val _uiState = MutableStateFlow(CartUiState())
    val uiState: StateFlow<CartUiState> = _uiState.asStateFlow()

    init {
        loadCartItems()
    }

    private fun loadCartItems() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            cartDao.getCartItems()
                .catch { error ->
                    _uiState.update { state ->
                        state.copy(
                            isLoading = false,
                            error = error.message ?: "Sepet yüklenirken bir hata oluştu"
                        )
                    }
                }
                .collect { items ->
                    _uiState.update { state ->
                        state.copy(
                            isLoading = false,
                            cartItems = items.map { it.toCartItem() },
                            totalAmount = items.sumOf { it.price * it.quantity }
                        )
                    }
                }
        }
    }

    fun updateQuantity(cartItem: CartItem, newQuantity: Int) {
        if (newQuantity < 1) return
        viewModelScope.launch {
            cartDao.updateCartItemQuantity(cartItem.id.toInt(), newQuantity)
        }
    }

    fun removeFromCart(cartItem: CartItem) {
        viewModelScope.launch {
            cartDao.deleteCartItem(
                CartItemEntity(
                    id = cartItem.id.toInt(),
                    productId = 0, // Varsayılan değer
                    name = cartItem.name,
                    price = cartItem.price,
                    imageUrl = cartItem.imageUrl,
                    quantity = cartItem.quantity,
                    selectedSize = cartItem.selectedSize,
                    selectedColor = cartItem.selectedColor
                )
            )
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            cartDao.clearCart()
        }
    }
}

data class CartUiState(
    val isLoading: Boolean = false,
    val cartItems: List<CartItem> = emptyList(),
    val totalAmount: Double = 0.0,
    val error: String? = null
) 