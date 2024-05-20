package com.example.testapplication.data.mapper

import com.example.testapplication.data.remote.dto.ObjectResponse
import com.example.testapplication.presentation.feature_product.data.Product

interface ProductMapper {
    fun mapObjectResponseToProduct(objectResponse: ObjectResponse): Product
    fun mapProductToObjectResponse(product: Product): ObjectResponse
}