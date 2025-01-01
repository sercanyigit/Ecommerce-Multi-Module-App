package com.example.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("SELECT * FROM products WHERE isFavorite = 1")
    fun getFavoriteProducts(): Flow<List<ProductEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: ProductEntity)
    
    @Delete
    suspend fun deleteProduct(product: ProductEntity)
    
    @Query("UPDATE products SET isFavorite = :isFavorite WHERE id = :productId")
    suspend fun updateFavoriteStatus(productId: Int, isFavorite: Boolean)
} 