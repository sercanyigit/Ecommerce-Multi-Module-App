package com.sercan.ecommerce.database.di

import android.content.Context
import androidx.room.Room
import com.sercan.ecommerce.database.AppDatabase
import com.sercan.ecommerce.database.dao.CartDao
import com.sercan.ecommerce.database.dao.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    
    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "ecommerce.db"
        ).build()
    }
    
    @Provides
    @Singleton
    fun provideProductDao(
        database: AppDatabase
    ): ProductDao {
        return database.productDao()
    }
    
    @Provides
    @Singleton
    fun provideCartDao(
        database: AppDatabase
    ): CartDao {
        return database.cartDao()
    }
} 