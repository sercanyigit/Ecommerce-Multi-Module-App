package com.sercan.ecommerce.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

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
) 