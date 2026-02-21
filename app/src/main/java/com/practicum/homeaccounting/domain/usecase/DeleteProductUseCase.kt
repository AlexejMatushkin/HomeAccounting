package com.practicum.homeaccounting.domain.usecase

import com.practicum.homeaccounting.domain.Product
import com.practicum.homeaccounting.domain.repository.ProductRepository
import javax.inject.Inject

class DeleteProductUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(product: Product) = repository.deleteProduct(product)
}
