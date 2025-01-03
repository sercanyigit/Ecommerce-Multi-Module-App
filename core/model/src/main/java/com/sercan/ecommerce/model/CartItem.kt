package com.sercan.ecommerce.model

data class CartItem(
    val id: Long,
    val name: String,
    val description: String = "",
    val price: Double,
    val imageUrl: String,
    val quantity: Int,
    val selectedSize: String?,
    val selectedColor: String?
) 