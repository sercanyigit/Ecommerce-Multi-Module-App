package com.sercan.ecommerce.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sercan.ecommerce.model.CartItem

@Entity(tableName = "cart_items")
data class CartItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val productId: Int,
    val name: String,
    val price: Double,
    val imageUrl: String,
    val quantity: Int,
    val selectedSize: String?,
    val selectedColor: String?
) {
    fun toCartItem() = CartItem(
        id = id.toLong(),
        name = name,
        description = "",
        price = price,
        imageUrl = imageUrl,
        quantity = quantity,
        selectedSize = selectedSize,
        selectedColor = selectedColor
    )
} 