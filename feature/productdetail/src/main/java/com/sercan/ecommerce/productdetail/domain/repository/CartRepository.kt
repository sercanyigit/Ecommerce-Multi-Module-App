package com.sercan.ecommerce.productdetail.domain.repository

import com.sercan.ecommerce.productdetail.domain.model.CartItem
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    fun getCartItemCount(): Flow<Int>
    suspend fun addToCart(cartItem: CartItem)
    suspend fun removeFromCart(cartItem: CartItem)
    suspend fun updateCartItemQuantity(cartItem: CartItem, quantity: Int)
    fun getCartItems(): Flow<List<CartItem>>
} 