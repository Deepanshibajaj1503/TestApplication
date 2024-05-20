package com.example.testapplication.di

import com.example.testapplication.data.mapper.ProductMapper
import com.example.testapplication.data.mapper.ProductMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindsProductMapper(productMapperImpl: ProductMapperImpl): ProductMapper
}