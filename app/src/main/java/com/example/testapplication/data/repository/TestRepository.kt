package com.example.testapplication.data.repository

import com.example.testapplication.presentation.feature_product.data.Product
import kotlinx.coroutines.flow.Flow

interface TestRepository {
    suspend fun getProducts(): Flow<List<Product>>
    suspend fun getProductInfo(productId: String): Flow<Product>
    suspend fun addProduct(product: Product): Flow<String>
}