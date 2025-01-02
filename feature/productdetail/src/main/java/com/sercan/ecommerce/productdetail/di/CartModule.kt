package com.sercan.ecommerce.productdetail.di

import com.sercan.ecommerce.database.dao.CartDao
import com.sercan.ecommerce.productdetail.data.repository.CartRepositoryImpl
import com.sercan.ecommerce.productdetail.domain.repository.CartRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CartModule {
    
    @Provides
    @Singleton
    fun provideCartRepository(cartDao: CartDao): CartRepository {
        return CartRepositoryImpl(cartDao)
    }
} 