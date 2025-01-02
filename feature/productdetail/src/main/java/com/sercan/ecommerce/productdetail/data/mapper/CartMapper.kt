package com.sercan.ecommerce.productdetail.data.mapper

import com.sercan.ecommerce.database.entity.CartItemEntity
import com.sercan.ecommerce.productdetail.domain.model.CartItem

fun CartItemEntity.toCartItem(): CartItem {
    return CartItem(
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

fun CartItem.toEntity(): CartItemEntity {
    return CartItemEntity(
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