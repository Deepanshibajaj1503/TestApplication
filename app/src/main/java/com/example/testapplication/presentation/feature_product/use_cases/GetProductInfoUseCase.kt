package com.example.testapplication.presentation.feature_product.use_cases

import com.example.testapplication.data.repository.TestRepository
import com.example.testapplication.presentation.feature_product.data.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductInfoUseCase @Inject constructor(private val testRepository: TestRepository) {
    suspend operator fun invoke(productId: String): Flow<Product> =
        testRepository.getProductInfo(productId = productId)
}