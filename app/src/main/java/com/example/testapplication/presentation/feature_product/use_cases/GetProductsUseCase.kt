package com.example.testapplication.presentation.feature_product.use_cases

import com.example.testapplication.data.repository.TestRepository
import com.example.testapplication.presentation.feature_product.data.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val testRepository: TestRepository
) {
   suspend operator fun invoke(): Flow<List<Product>> = testRepository.getProducts()

}