package com.example.mercadolibreapp.requestmanager.di

import com.example.mercadolibreapp.data.RemoteProductsDataSource
import com.example.mercadolibreapp.requestmanager.APIConstants
import com.example.mercadolibreapp.requestmanager.ProductRequest
import com.example.mercadolibreapp.requestmanager.ProductRetrofitDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class APIModule {
    @Provides
    fun productRequestProvider(
        @Named("baseUrl") baseUrl: String
    ) = ProductRequest(baseUrl)

    @Provides
    @Singleton
    @Named("baseUrl")
    fun baseUrlProvider():String = APIConstants.BASE_API_URL

    @Provides
    fun remoteProductDataSourceProvider(
        productRequest: ProductRequest
    ):RemoteProductsDataSource = ProductRetrofitDataSource(productRequest)
}