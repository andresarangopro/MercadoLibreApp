package com.example.mercadolibreapp.requestmanager

import com.example.mercadolibreapp.requestmanager.APIConstants.ENDPOINT_PRODUCTS
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {
    @GET(ENDPOINT_PRODUCTS)
    fun getProductsBySearch(@Query("q") q: String?,@Query("limit") limit: Int?): Single<ProductResponseServer>
}
