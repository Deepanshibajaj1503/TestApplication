package com.example.testapplication.data.repository


import com.example.testapplication.data.mapper.ProductMapper
import com.example.testapplication.data.mapper.ProductMapperImpl
import com.example.testapplication.data.remote.service.ApiService
import com.example.testapplication.data.util.MockData
import io.mockk.*
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class DBRepositoryTest{

    private val apiService : ApiService = mockk()

    private lateinit var dbRepository: TestRepositoryImpl
    private lateinit var productMapper: ProductMapper

    @Before
    fun setup(){
        productMapper = ProductMapperImpl()
        dbRepository = TestRepositoryImpl(
            apiService = apiService,
            productMapper = productMapper
        )
    }

    @After
    fun tearDown(){
        unmockkAll()
    }

    @Test
    fun testGetProducts(){
        coEvery {
            apiService.getObjects()
        } returns Response.success(listOf(MockData.objectResponse))

        runBlocking {
            val result = dbRepository.getProducts().count()
            assertEquals(result,1)
        }
    }

}