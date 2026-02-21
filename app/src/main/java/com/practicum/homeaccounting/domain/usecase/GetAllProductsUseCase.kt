package com.practicum.homeaccounting.domain.usecase

import com.practicum.homeaccounting.domain.Product
import com.practicum.homeaccounting.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(): Flow<List<Product>> = repository.getAllProducts()
}
