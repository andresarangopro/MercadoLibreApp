package com.example.mercadolibreapp.di

import com.example.mercadolibreapp.domain.Product
import com.example.mercadolibreapp.presentation.DetailProductViewModel
import com.example.mercadolibreapp.presentation.ProductListViewModel
import com.example.mercadolibreapp.usecases.GetProductsBySearchUseCase
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class DetailProductModule(private val product:Product) {

    @Provides
    fun detailProductViewModel(
    )= DetailProductViewModel(
        product
    )
}

@Subcomponent(modules = [(DetailProductModule::class)])
interface DetailProductComponent{
    val detailProductViewModel:DetailProductViewModel
}