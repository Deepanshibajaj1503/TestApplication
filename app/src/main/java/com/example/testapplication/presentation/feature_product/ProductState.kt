package com.example.testapplication.presentation.feature_product

import com.example.testapplication.presentation.feature_product.data.Product

data class ProductState(
    val isLoading: Boolean = false,
    val productList: List<Product> = mutableListOf(),
    val product: Product? = null
)