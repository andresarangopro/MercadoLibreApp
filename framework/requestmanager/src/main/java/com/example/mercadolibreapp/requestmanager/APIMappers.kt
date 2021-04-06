package com.example.mercadolibreapp.requestmanager

import android.util.Log
import com.example.mercadolibreapp.domain.Address
import com.example.mercadolibreapp.domain.Product
import com.example.mercadolibreapp.domain.Seller
import com.example.mercadolibreapp.domain.Shipping

fun ProductResponseServer.toProductDomainList():List<Product> = results.map{
    it.run{
        Log.d("pr","${it}")
        Product(
            id?:"0",
            title,
            seller.toDomainSeller(),
            price,
            available_quantity,
            thumbnail,
            condition,
            address.toDomainAddress(),
            shipping.toDomainShipping(),
        )
    }
}

fun SellerServer.toDomainSeller()=Seller(
    id
)

fun AddressServer.toDomainAddress()= Address(
    stateId,
    stateName,
    cityId,
    cityName
)

fun ShippingServer.toDomainShipping()= Shipping(
    freeShipping
)