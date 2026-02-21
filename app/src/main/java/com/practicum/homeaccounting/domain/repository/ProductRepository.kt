package com.practicum.homeaccounting.domain.repository

import com.practicum.homeaccounting.domain.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    fun getAllProducts(): Flow<List<Product>>

    suspend fun getProductById(id: String): Product?

    suspend fun addProduct(product: Product)

    suspend fun updateProduct(product: Product)

    suspend fun deleteProduct(product: Product)

    suspend fun deleteExpiredProducts()
}
