package com.example.testapplication.presentation.feature_product

sealed class ProductUIEvent {
    data class ShowSnackBar(val message: String) : ProductUIEvent()
}
