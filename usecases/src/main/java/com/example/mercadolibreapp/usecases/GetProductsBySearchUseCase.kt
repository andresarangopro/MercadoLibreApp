package com.example.mercadolibreapp.usecases

import com.example.mercadolibreapp.data.ProductRepository
import com.example.mercadolibreapp.domain.Product
import io.reactivex.Single

class GetProductsBySearchUseCase(
    private val productRepository: ProductRepository
) {

     fun invoke(searchedProduct:String, limit:Int): Single<List<Product>> = productRepository.getProductsBySearch(searchedProduct,limit)
}
