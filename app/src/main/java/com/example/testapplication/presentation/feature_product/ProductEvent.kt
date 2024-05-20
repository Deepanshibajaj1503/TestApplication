package com.example.testapplication.presentation.feature_product

import com.example.testapplication.presentation.feature_product.data.Product


sealed class ProductEvent {
    object GetProducts : ProductEvent()
    data class OnAddProductClicked(
        val name: String,
        val year: String,
        val price: String,
        val cpuModel: String,
        val hardDiskSize: String
    ) : ProductEvent()
    data class OnProductClicked(val product: Product) : ProductEvent()
    data class GetProductInfo(val productId: String) : ProductEvent()
}