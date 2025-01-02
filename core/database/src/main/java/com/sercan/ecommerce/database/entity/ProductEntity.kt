package com.sercan.ecommerce.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sercan.ecommerce.model.Product

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val price: Double,
    val imageUrl: String,
    val description: String,
    val isFavorite: Boolean
)

fun ProductEntity.toProduct() = Product(
    id = id,
    name = name,
    price = price,
    imageUrl = imageUrl,
    description = description,
    isFavorite = isFavorite
)

fun Product.toEntity() = ProductEntity(
    id = id,
    name = name,
    price = price,
    imageUrl = imageUrl,
    description = description,
    isFavorite = isFavorite
) 