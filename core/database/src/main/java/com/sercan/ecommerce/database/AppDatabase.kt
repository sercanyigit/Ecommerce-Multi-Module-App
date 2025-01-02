package com.sercan.ecommerce.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sercan.ecommerce.database.dao.ProductDao
import com.sercan.ecommerce.database.entity.ProductEntity

@Database(
    entities = [ProductEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
} 