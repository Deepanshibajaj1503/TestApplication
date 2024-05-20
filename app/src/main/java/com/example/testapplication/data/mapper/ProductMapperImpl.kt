package com.example.testapplication.data.mapper

import com.example.testapplication.data.remote.dto.Data
import com.example.testapplication.data.remote.dto.ObjectResponse
import com.example.testapplication.presentation.feature_product.data.Product
import com.example.testapplication.presentation.feature_product.data.ProductInfo
import javax.inject.Inject

class ProductMapperImpl @Inject constructor() : ProductMapper {

    override fun mapObjectResponseToProduct(objectResponse: ObjectResponse): Product =
        with(objectResponse) {
            Product(id = id ?: "",
                name = name ?: "",
                productInfo = data?.let { mapDataToProductInfo(it) })
        }

    override fun mapProductToObjectResponse(product: Product): ObjectResponse =
        ObjectResponse(
            id = product.id,
            name = product.name,
            data = Data(
                capacity = product.productInfo?.capacity,
                generation = product.productInfo?.generation,
                price = product.productInfo?.price,
                cpuModel = product.productInfo?.cpuModel,
                hardDiskSize = product.productInfo?.hardDiskSize,
                year = product.productInfo?.year,
            )
        )


    private fun mapDataToProductInfo(data: Data): ProductInfo = with(data) {
        ProductInfo(
            capacity = capacity,
            generation = generation,
            price = price,
        )
    }
}