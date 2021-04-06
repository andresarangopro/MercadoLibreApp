package com.example.mercadolibreapp.data

import com.example.mercadolibreapp.domain.Product
import io.reactivex.Single

interface RemoteProductsDataSource {
    fun getProductsBySearch(searchedProduct: String,limit:Int): Single<List<Product>>
}
