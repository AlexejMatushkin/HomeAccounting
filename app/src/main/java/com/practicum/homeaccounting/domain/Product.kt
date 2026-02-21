package com.practicum.homeaccounting.domain

import java.util.UUID

data class Product(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val expiryDate: Long,
    val quantity: Int,
    val category: ProductCategory,
    val notes: String? = null,
    val createdAt: Long = System.currentTimeMillis()
)

enum class ProductCategory {
    DAIRY,      // Молочные
    MEAT,       // Мясные
    VEGETABLES, // Овощи
    FRUITS,     // Фрукты
    BAKERY,     // Выпечка
    BEVERAGES,  // Напитки
    OTHER       // Другое
}
