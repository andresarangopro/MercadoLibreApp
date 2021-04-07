package com.example.mercadolibreapp.di

import android.app.Application
import com.example.mercadolibreapp.data.di.RepositoryModule
import com.example.mercadolibreapp.requestmanager.di.APIModule
import com.example.mercadolibreapp.usecases.di.UseCaseModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    APIModule::class,
    RepositoryModule::class,
    UseCaseModule::class
])
interface MercadoLibreAppComponent {
    fun inject(module: ProductListBySearchModule):ProductListBySearchComponent
    fun inject(module:DetailProductModule ):DetailProductComponent

    @Component.Factory
    interface Favorite{
        fun create(@BindsInstance app:Application):MercadoLibreAppComponent
    }
}