package com.example.testapplication.presentation.feature_product

import com.example.testapplication.data.util.MockData
import com.example.testapplication.presentation.feature_product.use_cases.AddProductUseCase
import com.example.testapplication.presentation.feature_product.use_cases.GetProductInfoUseCase
import com.example.testapplication.presentation.feature_product.use_cases.GetProductsUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ProductViewModelTest {

    private lateinit var productViewModel: ProductViewModel

    @MockK
    private val getProductInfoUseCase: GetProductInfoUseCase = mockk()

    @MockK
    private val addProductUseCase: AddProductUseCase = mockk()

    @MockK
    private val getProductUseCase: GetProductsUseCase = mockk()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setup() {
    }

    private fun initViewModel() {
        productViewModel = ProductViewModel(
            getProductInfoUseCase = getProductInfoUseCase,
            getProductsUseCase = getProductUseCase,
            addProductUseCase = addProductUseCase,
        )
    }

    @Test
    fun `when getProducts returns Success`() = runBlocking {
        coEvery { getProductUseCase() } returns flowOf(listOf(MockData.product))
        initViewModel()
        productViewModel.onEvent(ProductEvent.GetProducts)
        coVerify { getProductUseCase.invoke() }
        delay(200)
        assert(productViewModel.state.value.productList.isNotEmpty())
    }

    @After
    fun tearDown(){
        unmockkAll()
    }

}