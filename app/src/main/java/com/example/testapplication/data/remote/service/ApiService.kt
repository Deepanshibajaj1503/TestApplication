package com.example.testapplication.data.remote.service

import com.example.testapplication.data.remote.dto.ObjectResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("objects")
    suspend fun getObjects(): Response<List<ObjectResponse>>

    @GET("objects/{id}")
    suspend fun getObject(@Path("id") objectId: Int): Response<ObjectResponse>

    @POST("objects")
    suspend fun addObject(@Body objectResponse: ObjectResponse) : Response<ObjectResponse>
}