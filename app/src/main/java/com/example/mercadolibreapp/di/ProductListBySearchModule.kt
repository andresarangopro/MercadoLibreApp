package com.example.mercadolibreapp.di

import com.example.mercadolibreapp.presentation.ProductListViewModel
import com.example.mercadolibreapp.usecases.GetProductsBySearchUseCase
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class ProductListBySearchModule {

    @Provides
    fun productListBySearchViewModel(
        getProductsBySearchUseCase: GetProductsBySearchUseCase
    )= ProductListViewModel(
        getProductsBySearchUseCase
    )
}

@Subcomponent(modules = [(ProductListBySearchModule::class)])
interface ProductListBySearchComponent{
    val productListBySearchViewModel:ProductListViewModel
}