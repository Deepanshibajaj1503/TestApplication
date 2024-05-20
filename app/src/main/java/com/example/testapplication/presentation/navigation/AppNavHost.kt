package com.example.testapplication.presentation.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.testapplication.presentation.feature_product.ProductScreen
import com.example.testapplication.presentation.feature_home.HomeScreen
import com.example.testapplication.presentation.feature_product.AddProductScreen
import com.example.testapplication.presentation.feature_product.ProductEvent
import com.example.testapplication.presentation.feature_product.ProductInfoScreen
import com.example.testapplication.presentation.feature_product.ProductInfoScreenDestination
import com.example.testapplication.presentation.feature_product.ProductState
import com.example.testapplication.presentation.feature_product.ProductUIEvent
import com.example.testapplication.presentation.feature_splash.SplashScreen
import kotlinx.coroutines.flow.SharedFlow


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    startDestination: String = NavigationItem.Splash.route,
    productState: ProductState,
    onProductEvent: (event: ProductEvent) -> Unit,
    showSnackBar: (message: String) -> Unit,
    productEventFlow: SharedFlow<ProductUIEvent>,
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Splash.route) {
            SplashScreen(navHostController)
        }
        composable(NavigationItem.Home.route) {
            HomeScreen(navHostController)
        }
        composable(NavigationItem.ProductScreen.route) {
            ProductScreen(
                navHostController,
                state = productState,
                onEvent = onProductEvent,
                eventFlow = productEventFlow,
                showSnackBar = showSnackBar,
            )
        }
        composable(
            route = ProductInfoScreenDestination.route,
            arguments = ProductInfoScreenDestination.argumentList
        ) { backStackEntry ->
            val (productId, productName) = ProductInfoScreenDestination.parseArguments(
                backStackEntry
            )
            ProductInfoScreen(
                state = productState,
                productId = productId,
                productName = productName,
                navHostController = navHostController,
                onEvent = onProductEvent,
            )
        }
        composable(route = NavigationItem.AddProductScreen.route){
            AddProductScreen(
                navHostController = navHostController,
                state = productState,
                onEvent = onProductEvent,
                eventFlow = productEventFlow,
                showSnackBar = showSnackBar
            )
        }
    }

}


