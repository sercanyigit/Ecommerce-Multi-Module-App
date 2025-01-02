package com.sercan.ecommerce.productdetail.data.repository

import com.sercan.ecommerce.database.dao.CartDao
import com.sercan.ecommerce.database.entity.CartItemEntity
import com.sercan.ecommerce.productdetail.domain.model.CartItem
import com.sercan.ecommerce.productdetail.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val cartDao: CartDao
) : CartRepository {
    
    override fun getCartItemCount(): Flow<Int> = cartDao.getCartItemCount()

    override suspend fun addToCart(cartItem: CartItem) {
        cartDao.insertCartItem(cartItem.toEntity())
    }

    override suspend fun removeFromCart(cartItem: CartItem) {
        cartDao.deleteCartItem(cartItem.toEntity())
    }

    override suspend fun updateCartItemQuantity(cartItem: CartItem, quantity: Int) {
        cartDao.updateCartItemQuantity(cartItem.id, quantity)
    }

    override fun getCartItems(): Flow<List<CartItem>> {
        return cartDao.getCartItems().map { entities ->
            entities.map { it.toDomainModel() }
        }
    }

    private fun CartItem.toEntity() = CartItemEntity(
        id = id,
        productId = productId,
        name = name,
        price = price,
        imageUrl = imageUrl,
        quantity = quantity,
        selectedSize = selectedSize,
        selectedColor = selectedColor
    )

    private fun CartItemEntity.toDomainModel() = CartItem(
        id = id,
        productId = productId,
        name = name,
        price = price,
        imageUrl = imageUrl,
        quantity = quantity,
        selectedSize = selectedSize,
        selectedColor = selectedColor
    )
} 