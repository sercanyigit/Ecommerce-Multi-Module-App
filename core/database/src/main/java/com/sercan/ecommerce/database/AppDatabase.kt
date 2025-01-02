package com.sercan.ecommerce.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sercan.ecommerce.database.converter.Converters
import com.sercan.ecommerce.database.dao.CartDao
import com.sercan.ecommerce.database.dao.ProductDao
import com.sercan.ecommerce.database.entity.CartItemEntity
import com.sercan.ecommerce.database.entity.ProductEntity

@Database(
    entities = [
        ProductEntity::class,
        CartItemEntity::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun cartDao(): CartDao
} 