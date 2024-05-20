package com.example.testapplication.presentation.feature_product

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.NavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.testapplication.data.util.MockData
import com.example.testapplication.data.util.TestTag.PRODUCT_SCREEN_LAZY_COLUMN
import com.example.testapplication.data.util.TestTag.PRODUCT_SCREEN_SURFACE
import com.example.testapplication.presentation.feature_product.data.Product
import com.example.testapplication.presentation.feature_product.use_cases.AddProductUseCase
import com.example.testapplication.presentation.feature_product.use_cases.GetProductInfoUseCase
import com.example.testapplication.presentation.feature_product.use_cases.GetProductsUseCase
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.flow.flowOf
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProductScreenUiTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @RelaxedMockK
    private lateinit var productViewModel: ProductViewModel

    @RelaxedMockK
    private val getProductInfoUseCase: GetProductInfoUseCase = mockk()

    @RelaxedMockK
    private val addProductUseCase: AddProductUseCase = mockk()

    @RelaxedMockK
    private val getProductUseCase: GetProductsUseCase = mockk()

    private val mockNavController: NavHostController = mockk(relaxed = true)

    @Before
    fun setup() {
        coEvery { getProductUseCase() } returns flowOf(
            listOf()
        )
        coEvery { addProductUseCase(any()) } returns flowOf(
            ""
        )
        coEvery { getProductInfoUseCase(any()) } returns flowOf(
            Product()
        )
        productViewModel = ProductViewModel(
            getProductInfoUseCase = getProductInfoUseCase,
            addProductUseCase = addProductUseCase,
            getProductsUseCase = getProductUseCase
        )
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    private fun openProductScreen(productList: List<Product>, product: Product) {
        composeTestRule.setContent {
            ProductScreen(
                navHostController = mockNavController,
                state = productViewModel.state.value.copy(
                    isLoading = false,
                    productList = productList,
                    product = product
                ),
                onEvent = productViewModel::onEvent,
                eventFlow =productViewModel.eventFlow,
                showSnackBar = {}
            )
        }
    }

    @Test
    fun isProductScreenVisible(){
        val productList = listOf(MockData.product)
        val product = MockData.product
        openProductScreen(productList = productList, product = product)
        composeTestRule.onNodeWithTag(PRODUCT_SCREEN_SURFACE).assertIsDisplayed()
        composeTestRule.onNodeWithTag(PRODUCT_SCREEN_LAZY_COLUMN).assertIsDisplayed()
    }

}