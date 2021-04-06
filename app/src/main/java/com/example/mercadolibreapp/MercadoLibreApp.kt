package com.example.mercadolibreapp

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.mercadolibreapp.di.DaggerMercadoLibreAppComponent
import com.example.mercadolibreapp.di.DaggerMercadoLibreAppComponent.factory
import com.example.mercadolibreapp.di.MercadoLibreAppComponent

class MercadoLibreApp:Application() {

    lateinit var component:MercadoLibreAppComponent

    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        component = initAppComponent()
    }

    //endregion
    private fun initAppComponent() =   DaggerMercadoLibreAppComponent.factory().create(this)
}