package com.example.mercadolibreapp.usecases.di

import com.example.mercadolibreapp.data.ProductRepository
import com.example.mercadolibreapp.usecases.GetProductsBySearchUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun getProductsBySearchUseCaseProvider(productRepository: ProductRepository)=
        GetProductsBySearchUseCase(productRepository)
}