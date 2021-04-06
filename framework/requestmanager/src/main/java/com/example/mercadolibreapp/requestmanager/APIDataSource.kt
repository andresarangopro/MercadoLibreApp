package com.example.mercadolibreapp.requestmanager

import com.example.mercadolibreapp.data.RemoteProductsDataSource
import com.example.mercadolibreapp.domain.Product
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProductRetrofitDataSource(
    private val productRequest: ProductRequest
): RemoteProductsDataSource {

    override fun getProductsBySearch(searchedProduct: String, limit:Int): Single<List<Product>> {
        return productRequest
            .getService<ProductService>()
            .getProductsBySearch(searchedProduct,limit)
            .map(ProductResponseServer::toProductDomainList)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

}