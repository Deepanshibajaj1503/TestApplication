package com.example.testapplication.data.mapper

import com.example.testapplication.data.util.MockData
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals


class ProductMapperImplTest {

    private lateinit var productMapper: ProductMapper

    @Before
    fun setup() {
        productMapper = ProductMapperImpl()
    }


    @Test
    fun testMapObjectDtoToProduct() {
        val objectResponse = MockData.objectResponse
        val product = productMapper.mapObjectResponseToProduct(objectResponse)

        assertEquals(product.id, objectResponse.id)
        assertEquals(product.productInfo?.capacity, objectResponse.data?.capacity)
        assertEquals(product.name, objectResponse.name)
    }

}