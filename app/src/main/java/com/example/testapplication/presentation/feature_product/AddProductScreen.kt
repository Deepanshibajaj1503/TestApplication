package com.example.testapplication.presentation.feature_product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavHostController
import com.example.testapplication.ui.utils.CircularProgressBar
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductScreen(
    navHostController: NavHostController,
    state: ProductState,
    onEvent: (event: ProductEvent) -> Unit,
    eventFlow: SharedFlow<ProductUIEvent>,
    showSnackBar: (message: String) -> Unit,
) {
    var productName by rememberSaveable { mutableStateOf("") }
    var productYear by rememberSaveable { mutableStateOf("") }
    var productPrice by rememberSaveable { mutableStateOf("") }
    var cpuModel by rememberSaveable { mutableStateOf("") }
    var hardDiskSize by rememberSaveable { mutableStateOf("") }

    Column {
        Text("Add Product Screen", fontSize = 20.sp, modifier = Modifier.padding(20.dp))

        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .verticalScroll(
                    rememberScrollState()
                ),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = productName,
                onValueChange = { productName = it },
                placeholder = { Text(text = "eg. Iphone 15 PRO") },
                singleLine = true
            )

            Spacer(modifier = Modifier.padding(top = 10.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = productYear,
                onValueChange = { if (it.isDigitsOnly()) productYear = it },
                placeholder = { Text(text = "eg. 2022") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.padding(top = 10.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = productPrice,
                onValueChange = { if (it.isNotBlank()) productPrice = it },
                placeholder = { Text(text = "eg. 189.99") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.padding(top = 10.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = cpuModel,
                onValueChange = { cpuModel = it },
                placeholder = { Text(text = "eg. Intel Core i9") },
                singleLine = true,
            )

            Spacer(modifier = Modifier.padding(top = 10.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = hardDiskSize,
                onValueChange = { hardDiskSize = it },
                placeholder = { Text(text = "eg. 1 TB") },
                singleLine = true,
            )

            Spacer(modifier = Modifier.padding(top = 20.dp))

            if(state.isLoading){
                CircularProgressBar()
            }else {
                Button(
                    onClick = {
                            onEvent(
                                ProductEvent.OnAddProductClicked(
                                    name = productName,
                                    year = productYear,
                                    price = productPrice,
                                    cpuModel = cpuModel,
                                    hardDiskSize = hardDiskSize
                                )
                            )
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Add Product")
                }
            }
        }
    }
    LaunchedEffect(key1 = true) {

        eventFlow.collectLatest {
            when (it) {
                is ProductUIEvent.ShowSnackBar -> showSnackBar(it.message)
            }
        }
    }
}