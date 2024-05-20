package com.example.testapplication.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ObjectResponse(
    @SerializedName("data")
    val data: Data?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?
)
