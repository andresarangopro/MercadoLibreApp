package com.example.mercadolibreapp.data

import com.example.mercadolibreapp.domain.Product
import io.reactivex.Single

class ProductRepository(
    private val remoteProductsDataSource: RemoteProductsDataSource
){

    fun getProductsBySearch(searchedProduct:String, limit:Int): Single<List<Product>> =
        remoteProductsDataSource.getProductsBySearch(searchedProduct,limit)

}