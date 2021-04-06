package com.example.mercadolibreapp.data.di

import com.example.mercadolibreapp.data.ProductRepository
import com.example.mercadolibreapp.data.RemoteProductsDataSource
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun productRepositoryProvider(
        remoteProductsDataSource: RemoteProductsDataSource
    ) = ProductRepository(remoteProductsDataSource)
}