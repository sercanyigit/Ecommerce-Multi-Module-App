package com.example.model

data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val imageUrl: String,
    val description: String = "",
    val isFavorite: Boolean = false,
    val category: String = "",
    val sizes: List<String> = emptyList(),
    val colors: List<String> = emptyList()
) 