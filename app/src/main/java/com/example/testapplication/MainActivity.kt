package com.example.testapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.navigation.compose.rememberNavController
import com.example.testapplication.presentation.feature_product.ProductViewModel
import com.example.testapplication.presentation.navigation.AppNavHost
import com.example.testapplication.ui.theme.TestApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val productViewModel: ProductViewModel by viewModels()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestApplicationTheme {

                val snackBarHostState = remember { SnackbarHostState() }
                val focusManager = LocalFocusManager.current
                val snackBarScope: CoroutineScope = rememberCoroutineScope()

                Scaffold(snackbarHost = { SnackbarHost(snackBarHostState) },
                    content = {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = Color.White
                        ) {
                            AppNavHost(
                                navHostController = rememberNavController(),
                                productState = productViewModel.state.value,
                                onProductEvent = productViewModel::onEvent,
                                productEventFlow = productViewModel.eventFlow,
                                showSnackBar = { message ->
                                    showSnackBar(
                                        focusManager = focusManager,
                                        snackBarScope = snackBarScope,
                                        snackBarHostState = snackBarHostState,
                                        message = message
                                    )
                                }
                            )
                        }
                    })
                // A surface cont  ainer using the 'background' color from the theme
                //ProductScreen(state = productViewModel.state.value)
            }
        }
    }

    private fun showSnackBar(
        focusManager: FocusManager,
        snackBarScope: CoroutineScope,
        snackBarHostState: SnackbarHostState,
        message: String
    ) {
        focusManager.clearFocus()
        snackBarScope.launch {
            snackBarHostState.showSnackbar(message = message)
        }
    }
}