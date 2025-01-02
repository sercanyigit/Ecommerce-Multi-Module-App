package com.sercan.ecommerce.database.di

import android.content.Context
import androidx.room.Room
import com.sercan.ecommerce.database.AppDatabase
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
    ): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "app_database"
    ).build()
    
    @Provides
    @Singleton
    fun provideProductDao(
        database: AppDatabase
    ): ProductDao = database.productDao()
} 