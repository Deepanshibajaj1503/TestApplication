package com.example.testapplication.presentation.feature_product.use_cases

import com.example.testapplication.data.repository.TestRepository
import com.example.testapplication.presentation.feature_product.data.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddProductUseCase @Inject constructor(private val repository: TestRepository) {
    suspend operator fun invoke(product: Product): Flow<String> =
        repository.addProduct(product = product)
}