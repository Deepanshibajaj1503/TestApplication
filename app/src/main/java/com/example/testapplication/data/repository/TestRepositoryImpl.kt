package com.example.testapplication.data.repository

import com.example.testapplication.data.mapper.ProductMapper
import com.example.testapplication.data.remote.service.ApiService
import com.example.testapplication.presentation.feature_product.data.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TestRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val productMapper: ProductMapper,
) : TestRepository {
    override suspend fun getProducts(): Flow<List<Product>> = flow {
        val response = apiService.getObjects()
        if (response.isSuccessful && !response.body().isNullOrEmpty()) {
            response.body()?.let { objectResponseList ->
                val productList = objectResponseList.map {
                    productMapper.mapObjectResponseToProduct(objectResponse = it)
                }
                emit(productList)
            }
        }
    }

    override suspend fun getProductInfo(productId: String): Flow<Product> = flow {
        val response = apiService.getObject(objectId = productId.toInt())
        if (response.isSuccessful) {
            response.body()?.let { objectResponse ->
                val product =
                    productMapper.mapObjectResponseToProduct(objectResponse = objectResponse)
                emit(product)
            }
        }
    }

    override suspend fun addProduct(product: Product): Flow<String> = flow {
        val objectResponse = productMapper.mapProductToObjectResponse(product)
        val response = apiService.addObject(objectResponse = objectResponse)
        if (response.isSuccessful) {
            emit("Success")
        }
    }

}