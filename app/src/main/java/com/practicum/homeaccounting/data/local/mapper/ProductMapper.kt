package com.practicum.homeaccounting.data.local.mapper

import com.practicum.homeaccounting.data.local.entity.ProductEntity
import com.practicum.homeaccounting.domain.Product

fun ProductEntity.toDomain(): Product = Product(
    id = id,
    name = name,
    expiryDate = expiryDate,
    quantity = quantity,
    category = category,
    notes = notes,
    createdAt = createdAt
)

fun Product.toEntity(): ProductEntity = ProductEntity(
    id = id,
    name = name,
    expiryDate = expiryDate,
    quantity = quantity,
    category = category,
    notes = notes,
    createdAt = createdAt
)
