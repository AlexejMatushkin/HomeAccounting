package com.practicum.homeaccounting.di

import android.content.Context
import androidx.room.Room
import com.practicum.homeaccounting.data.local.dao.ProductDao
import com.practicum.homeaccounting.data.local.database.ProductDatabase
import com.practicum.homeaccounting.data.repository.ProductRepositoryImpl
import com.practicum.homeaccounting.domain.repository.ProductRepository
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
    fun provideProductDatabase(
        @ApplicationContext context: Context
    ): ProductDatabase = Room.databaseBuilder(
        context,
        ProductDatabase::class.java,
        "product_database"
    ).build()

    @Provides
    @Singleton
    fun provideProductDao(
        database: ProductDatabase
    ): ProductDao = database.productDao()

    @Provides
    @Singleton
    fun provideProductRepository(
        productDao: ProductDao
    ): ProductRepository = ProductRepositoryImpl(productDao)
}
