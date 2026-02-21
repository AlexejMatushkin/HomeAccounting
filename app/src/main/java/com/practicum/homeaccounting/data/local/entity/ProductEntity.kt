package com.practicum.homeaccounting.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.practicum.homeaccounting.domain.ProductCategory

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val expiryDate: Long,
    val quantity: Int,
    val category: ProductCategory,
    val notes: String?,
    val createdAt: Long
)
