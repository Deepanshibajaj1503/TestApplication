package com.example.testapplication.presentation.feature_product

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.testapplication.R
import com.example.testapplication.data.util.TestTag.PRODUCT_SCREEN_LAZY_COLUMN
import com.example.testapplication.data.util.TestTag.PRODUCT_SCREEN_SURFACE
import com.example.testapplication.presentation.navigation.NavigationItem
import com.example.testapplication.ui.utils.CircularProgressBar
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest


@Composable
fun ProductScreen(
    navHostController: NavHostController,
    state: ProductState,
    onEvent: (event: ProductEvent) -> Unit,
    eventFlow: SharedFlow<ProductUIEvent>,
    showSnackBar: (message: String) -> Unit,
) {
    Surface(modifier = Modifier.testTag(PRODUCT_SCREEN_SURFACE)) {
        val products = state.productList
        if (state.isLoading) {
            CircularProgressBar()
        } else {
            Column {
                Button(
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(end = 20.dp),
                    onClick = { navHostController.navigate(NavigationItem.AddProductScreen.route) }) {
                    Text(text = "Add Product")
                }
                LazyColumn(modifier = Modifier.testTag(PRODUCT_SCREEN_LAZY_COLUMN)) {
                    items(items = products, itemContent = { product ->
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable {
                                    navHostController.navigate(
                                        ProductInfoScreenDestination.getDestination(
                                            product.id ?: "",
                                            product.name ?: ""
                                        )
                                    )
                                }
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Image(
                                painterResource(id = R.drawable.image_placeholder),
                                contentDescription = "",
                                modifier = Modifier.size(40.dp)
                            )
                            Column {
                                Text(text = product.name ?: "", modifier = Modifier.padding(12.dp))
                            }
                        }
                        Divider()
                    })
                }
            }
        }
    }

    LaunchedEffect(key1 = true) {
        onEvent(ProductEvent.GetProducts)
        eventFlow.collectLatest { event ->
            when (event) {
                is ProductUIEvent.ShowSnackBar -> showSnackBar(event.message)
            }
        }
    }
}
