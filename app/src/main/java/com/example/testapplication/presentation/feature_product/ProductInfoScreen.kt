package com.example.testapplication.presentation.feature_product

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.testapplication.ui.utils.CircularProgressBar


@Composable
fun ProductInfoScreen(
    productId: String,
    productName: String,
    navHostController: NavHostController,
    onEvent: (event: ProductEvent) -> Unit,
    state: ProductState,
) {
    if (state.isLoading) {
        CircularProgressBar()
    } else {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "This is productId: $productId", textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "Product Name: ${state.product?.name}", textAlign = TextAlign.Center)

                state.product?.productInfo?.price?.let {
                    Text(
                        text = "Product Price: $it",
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
    LaunchedEffect(key1 = true) {
        onEvent(ProductEvent.GetProductInfo(productId = productId))
    }
}