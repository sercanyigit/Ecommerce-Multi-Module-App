package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database.dao.ProductDao
import com.example.database.entity.ProductEntity

@Database(
    entities = [ProductEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
} 