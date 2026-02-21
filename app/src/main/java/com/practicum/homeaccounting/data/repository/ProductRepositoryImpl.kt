package com.practicum.homeaccounting.data.repository

import com.practicum.homeaccounting.data.local.dao.ProductDao
import com.practicum.homeaccounting.data.local.mapper.toDomain
import com.practicum.homeaccounting.data.local.mapper.toEntity
import com.practicum.homeaccounting.domain.Product
import com.practicum.homeaccounting.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productDao: ProductDao
) : ProductRepository {

    override fun getAllProducts(): Flow<List<Product>> =
        productDao.getAllProducts().map { entities ->
            entities.map { it.toDomain() }
        }

    override suspend fun getProductById(id: String): Product? =
        productDao.getProductById(id)?.toDomain()

    override suspend fun addProduct(product: Product) =
        productDao.insertProduct(product.toEntity())

    override suspend fun updateProduct(product: Product) =
        productDao.updateProduct(product.toEntity())

    override suspend fun deleteProduct(product: Product) =
        productDao.deleteProduct(product.toEntity())

    override suspend fun deleteExpiredProducts() =
        productDao.deleteExpiredProducts(System.currentTimeMillis())
}
