package com.example.testapplication.presentation.feature_product

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapplication.presentation.feature_product.data.Product
import com.example.testapplication.presentation.feature_product.data.ProductInfo
import com.example.testapplication.presentation.feature_product.use_cases.AddProductUseCase
import com.example.testapplication.presentation.feature_product.use_cases.GetProductInfoUseCase
import com.example.testapplication.presentation.feature_product.use_cases.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val getProductInfoUseCase: GetProductInfoUseCase,
    private val addProductUseCase: AddProductUseCase,
) :
    ViewModel() {

    private val _state = mutableStateOf(ProductState())
    val state: State<ProductState> = _state

    private val _eventFlow = MutableSharedFlow<ProductUIEvent>()
    val eventFlow: SharedFlow<ProductUIEvent> = _eventFlow.asSharedFlow()

    init {}

    fun onEvent(event: ProductEvent) {
        when (event) {
            is ProductEvent.OnProductClicked -> {
                onProductClicked(event.product)
            }

            is ProductEvent.GetProductInfo -> {
                getProductInfo(event.productId)
            }

            is ProductEvent.GetProducts -> {
                getProducts()
            }

            is ProductEvent.OnAddProductClicked -> {
                onAddProductClicked(
                    name = event.name,
                    year = event.year,
                    price = event.price,
                    cpuModel = event.cpuModel,
                    hardDiskSize = event.hardDiskSize
                )
            }
        }
    }

    private fun onAddProductClicked(
        name: String,
        year: String,
        price: String,
        cpuModel: String,
        hardDiskSize: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                _state.value = state.value.copy(isLoading = true)
            }
            if (name.isEmpty()) {
                _eventFlow.emit(ProductUIEvent.ShowSnackBar("Please add name"))
                withContext(Dispatchers.Main) {
                    _state.value = state.value.copy(isLoading = false)
                }
                return@launch
            }
            if (year.isEmpty()) {
                _eventFlow.emit(ProductUIEvent.ShowSnackBar("Please add year"))
                withContext(Dispatchers.Main) {
                    _state.value = state.value.copy(isLoading = false)
                }
                return@launch
            }
            if (price.isEmpty()) {
                _eventFlow.emit(ProductUIEvent.ShowSnackBar("Please add price"))
                withContext(Dispatchers.Main) {
                    _state.value = state.value.copy(isLoading = false)
                }
                return@launch
            }
            if (hardDiskSize.isEmpty()) {
                _eventFlow.emit(ProductUIEvent.ShowSnackBar("Please add Hard Disk size"))
                withContext(Dispatchers.Main) {
                    _state.value = state.value.copy(isLoading = false)
                }
                return@launch
            }
            val product = Product(
                name = name,
                productInfo = ProductInfo(
                    year = year,
                    price = price,
                    cpuModel = cpuModel,
                    hardDiskSize = hardDiskSize
                ),
            )
            addProductUseCase.invoke(product = product).collectLatest { message ->
                withContext(Dispatchers.Main) {
                    _state.value = state.value.copy(isLoading = false)
                }
                if (message == "Success") {
                    _eventFlow.emit(ProductUIEvent.ShowSnackBar("Product added successfully"))
                }
            }
        }
    }

    private fun onProductClicked(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            _eventFlow.emit(ProductUIEvent.ShowSnackBar("${product.name} is clicked"))
        }
    }

    private fun getProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                _state.value = state.value.copy(isLoading = true)
            }
            getProductsUseCase().collectLatest {
                withContext(Dispatchers.Main) {
                    _state.value = state.value.copy(productList = it, isLoading = false)
                }
            }
        }
    }

    private fun getProductInfo(productId: String) {
        viewModelScope.launch(Dispatchers.IO) {

            withContext(Dispatchers.Main) {
                _state.value = state.value.copy(isLoading = true)
            }

            val product = state.value.productList.find { product -> productId == product.id }
            product?.id?.let { productId ->
                getProductInfoUseCase.invoke(productId = productId).collectLatest {
                    withContext(Dispatchers.Main) {
                        _state.value = state.value.copy(product = product, isLoading = false)
                    }
                }
            }
        }
    }

}