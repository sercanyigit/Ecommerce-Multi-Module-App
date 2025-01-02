package com.sercan.ecommerce.productdetail.domain.model

data class CartItem(
    val id: Int = 0,
    val productId: Int,
    val name: String,
    val price: Double,
    val imageUrl: String,
    val quantity: Int,
    val selectedSize: String?,
    val selectedColor: String?
) 