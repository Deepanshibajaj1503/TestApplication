package com.example.testapplication.data.util

import com.example.testapplication.data.remote.dto.Data
import com.example.testapplication.data.remote.dto.ObjectResponse
import com.example.testapplication.presentation.feature_product.data.Product
import com.example.testapplication.presentation.feature_product.data.ProductInfo

object MockData {

    val objectResponse  = ObjectResponse(
        data = Data(
            capacity = "64GB",
            generation = "6th",
            price = "120",
            cpuModel = "iNTEL",
            hardDiskSize = "4GB",
            year = "2022"
        ),
        id = "1",
        name = "IPHONE 15 PRO"
    )

    val product = Product(
        productInfo = ProductInfo(
            capacity = "64 GB",
            generation = "6th",
            price = "120"
        ),
        id = "1",
        name = "Google Pixel 6 Pro"
    )
}