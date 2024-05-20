package com.example.testapplication.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("capacity")
    val capacity: String?,
    @SerializedName("generation")
    val generation: String?,
    @SerializedName("price")
    val price: String?,
    @SerializedName("CPU model")
    val cpuModel: String?,
    @SerializedName("Hard disk size")
    val hardDiskSize: String?,
    @SerializedName("year")
    val year: String?,
)
