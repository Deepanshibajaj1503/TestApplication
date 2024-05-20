package com.example.testapplication.presentation.feature_product

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument

class ProductInfoScreenDestination {

    data class ProductInfoScreenArgs (
        val productId: String,
        val productName: String,
    )
    companion object {
        fun parseArguments(backStackEntry: NavBackStackEntry): ProductInfoScreenArgs {
            return ProductInfoScreenArgs(
                productId = backStackEntry.arguments?.getString("productId") ?: "",
                productName = backStackEntry.arguments?.getString("productName") ?: "",
            )
        }
        val argumentList: MutableList<NamedNavArgument>
            get() = mutableListOf(
                navArgument("productId") {
                    type = NavType.StringType
                },
                navArgument("productName") {
                    type = NavType.StringType
                },
            )
        fun getDestination(productId: String, productName: String): String {
            return "productInfo?" +
                    "productId=$productId," +
                    "productName=$productName" +
                    ""
        }
        val route = "productInfo?productId={productId},productName={productName}"
    }
}